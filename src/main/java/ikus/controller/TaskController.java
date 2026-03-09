package ikus.controller;

import ikus.dto.ApiResponse;
import ikus.entity.Task;
import ikus.entity.TaskStatus;
import ikus.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ApiResponse<Task> createTask(@RequestBody @Valid Task task,
                                        @RequestParam String projectId) {

        Task newTask = taskService.createTask(task, projectId);

        return new ApiResponse<>(1000, "Tạo công việc thành công", newTask);
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public ApiResponse<Task> assignTask(@PathVariable String taskId,
                                        @PathVariable String userId) {

        Task updatedTask = taskService.assignTask(taskId, userId);
        return new ApiResponse<>(1000, "Giao việc thành công", updatedTask);
    }

    @PutMapping("/{taskId}/status")
    public ApiResponse<Task> updateStatus(@PathVariable String taskId,
                                          @RequestParam TaskStatus status) {

        Task updatedTask = taskService.updateStatus(taskId, status);
        return new ApiResponse<>(1000, "Cập nhật trạng thái thành công", updatedTask);
    }

    @GetMapping("/project/{projectId}")
    public ApiResponse<List<Task>> getByProject(@PathVariable String projectId) {

        List<Task> tasks = taskService.getTasksByProject(projectId);
        return new ApiResponse<>(1000, "Lấy danh sách thành công", tasks);
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<Task>> getByUser(@PathVariable String userId) {

        List<Task> tasks = taskService.getTasksByUser(userId);
        return new ApiResponse<>(1000, "Lấy danh sách thành công", tasks);
    }
}