package ikus.controller;

import ikus.dto.ApiResponse;
import ikus.entity.User;
import ikus.entity.UserRole;
import ikus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ikus.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody User request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Lỗi: Tên đăng nhập '" + request.getUsername() + "' đã tồn tại!");
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        if (request.getRole() == null) {
            request.setRole(UserRole.USER);
        }

        User savedUser = userRepository.save(request);

        savedUser.setPassword("******");

        return new ApiResponse<>(1000, "Đăng ký thành công", savedUser);
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody User request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại!"));

        boolean isMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isMatch) {
            throw new RuntimeException("Sai mật khẩu!");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new ApiResponse<>(1000, "Đăng nhập thành công", token);
    }
}
