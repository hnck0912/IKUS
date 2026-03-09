package ikus.controller;

import ikus.entity.Task;
import ikus.entity.Project;
import ikus.entity.User;
import ikus.repository.TaskRepository;
import ikus.repository.ProjectRepository;
import ikus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Task createTask(@RequestBody Task task,
                           @RequestParam String projectId,
                           @RequestParam(required = false) String userId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        task.setProject(project);

        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            task.setAssignee(user);
        }

        return taskRepository.save(task);
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUser(@PathVariable String userId) {
        return taskRepository.findByAssigneeId(userId);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable String projectId) {
        return taskRepository.findByProjectId(projectId);
    }
}
