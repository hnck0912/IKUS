package ikus.service;

import ikus.entity.Project;
import ikus.entity.Task;
import ikus.entity.TaskStatus;
import ikus.entity.User;
import ikus.repository.ProjectRepository;
import ikus.repository.TaskRepository;
import ikus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    public Task createTask(Task task, String projectId) {
        System.out.println("=> Đang tạo task cho project: " + projectId);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay project co id nay!"));

        task.setProject(project);

        task.setStatus(TaskStatus.TODO);

        task.setDueDate(LocalDate.now().plusDays(7));

        return taskRepository.save(task);
    }

    public Task assignTask(String taskId, String userId) {
        System.out.println("=> Assigning task " + taskId + " to user " + userId);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task nay khong ton tai"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay user"));

        task.setAssignee(user);
        return taskRepository.save(task);
    }

    public Task updateStatus(String taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task khong ton tai!"));

        if (task.getStatus() == TaskStatus.DONE && newStatus != TaskStatus.TODO) {
            throw new RuntimeException("Task da hoan thanh roi, ko duoc sua status!");
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
