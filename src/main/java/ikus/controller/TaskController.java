package ikus.controller;

import ikus.entity.Task;
import ikus.entity.TaskStatus;
import ikus.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // API 1: Tạo Task (Mục 3)
    @PostMapping
    public Task createTask(@RequestBody Task task, @RequestParam String projectId) {
        return taskService.createTask(task, projectId);
    }

    // API 2: Gán User (Mục 5)
    // PUT /api/tasks/{taskId}/assign/{userId}
    @PutMapping("/{taskId}/assign/{userId}")
    public Task assignTask(@PathVariable String taskId, @PathVariable String userId) {
        return taskService.assignTask(taskId, userId);
    }

    // API 3: Update trạng thái (Mục 7)
    // PUT /api/tasks/{taskId}/status?status=DONE
    @PutMapping("/{taskId}/status")
    public Task updateStatus(@PathVariable String taskId, @RequestParam TaskStatus status) {
        return taskService.updateStatus(taskId, status);
    }

    // API 4: List theo Project (Mục 9)
    @GetMapping("/project/{projectId}")
    public List<Task> getByProject(@PathVariable String projectId) {
        return taskService.getTasksByProject(projectId);
    }

    // API 5: List theo User (Mục 10)
    @GetMapping("/user/{userId}")
    public List<Task> getByUser(@PathVariable String userId) {
        return taskService.getTasksByUser(userId);
    }
}