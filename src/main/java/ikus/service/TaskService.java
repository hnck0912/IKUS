package ikus.service;

import ikus.entity.*;
import ikus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired private TaskRepository taskRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private UserRepository userRepository;

    // 1. Tạo Task mới (Có Validate Project tồn tại - Mục 4)
    public Task createTask(Task task, String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Lỗi: Project ID " + projectId + " không tồn tại!"));

        task.setProject(project);
        task.setStatus(TaskStatus.TODO); // Mặc định là TODO
        task.setDueDate(LocalDate.now().plusDays(7)); // Mặc định deadline 1 tuần

        return taskRepository.save(task);
    }

    // 2. Gán Task cho User (Mục 5, 6)
    public Task assignTask(String taskId, String userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại!"));

        // (Mục 6: Tạm thời chúng ta coi như User nào cũng được assign.
        // Sau này có bảng ProjectMember sẽ check kỹ hơn sau).

        task.setAssignee(user);
        return taskRepository.save(task);
    }

    // 3. Update trạng thái (Có chặn nếu Task đã DONE - Mục 8)
    public Task updateStatus(String taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại!"));

        // Rule: Nếu đang là DONE thì không được chuyển sang trạng thái khác (trừ khi Reopen)
        if (task.getStatus() == TaskStatus.DONE && newStatus != TaskStatus.TODO) {
            throw new RuntimeException("Lỗi: Task đã xong (DONE) không được phép chỉnh sửa!");
        }

        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    // 4. Lấy danh sách
    public List<Task> getTasksByProject(String projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public List<Task> getTasksByUser(String userId) {
        return taskRepository.findByAssigneeId(userId);
    }
}
