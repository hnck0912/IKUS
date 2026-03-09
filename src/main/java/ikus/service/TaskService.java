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

    public Task createTask(Task task, String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Lỗi: Project ID " + projectId + " không tồn tại!"));

        task.setProject(project);
        task.setStatus(TaskStatus.TODO);
        task.setDueDate(LocalDate.now().plusDays(7));

        return taskRepository.save(task);
    }

    public Task assignTask(String taskId, String userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại!"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại!"));

        task.setAssignee(user);
        return taskRepository.save(task);
    }

    public Task updateStatus(String taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại!"));

        if (task.getStatus() == TaskStatus.DONE && newStatus != TaskStatus.TODO) {
            throw new RuntimeException("Lỗi: Task đã xong (DONE) không được phép chỉnh sửa!");
        }

        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByProject(String projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public List<Task> getTasksByUser(String userId) {
        return taskRepository.findByAssigneeId(userId);
    }
}
