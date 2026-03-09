package ikus;

import ikus.entity.Project;
import ikus.entity.Task;
import ikus.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;

@SpringBootApplication
public class IkusApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IkusApplication.class, args);
    }

    // 2. Override hàm run. Code test của bạn sẽ viết hết vào trong này.
    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== BẮT ĐẦU TEST CONSOLE (MỤC 12) ===");

        try {
            // --- COPY PHẦN CODE TEST CŨ VÀO ĐÂY ---

            // 1. Tạo Project
            Project projectA = new Project("PRJ-01", "Website E-commerce");

            // 2. Tạo User (Giả sử bạn đã fix lỗi UserRole rồi)
            User dev1 = new User("U1", "Nguyen Van A", "a@test.com", User.UserRole.STAFF);

            // 3. Tạo Task
            Task t1 = new Task("T1", "Database", Task.TaskPriority.HIGH, LocalDate.now());

            // 4. Thêm & Gán
            projectA.addTask(t1);
            t1.setAssignee(dev1);

            // 5. In kết quả
            projectA.printAllTasks();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=== KẾT THÚC TEST ===");
    }
}
