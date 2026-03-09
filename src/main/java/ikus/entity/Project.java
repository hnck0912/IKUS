package ikus.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Project {
    private String id;
    private String name;
    private List<Task> tasks; // Danh sách Task thuộc Project

    public Project(String id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    // --- Mục 9: Logic Add Task (có check trùng lặp) ---
    public void addTask(Task newTask) {
        // Check if task ID already exists
        boolean exists = tasks.stream().anyMatch(t -> t.getId().equals(newTask.getId()));
        if (exists) {
            System.out.println("[ERROR] Task with ID " + newTask.getId() + " already exists.");
            return;
        }
        tasks.add(newTask);
        System.out.println("[LOG] Added task: " + newTask.getTitle());
    }

    // --- Mục 9: Logic Delete Task ---
    public void removeTask(String taskId) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(taskId));
        if (removed) {
            System.out.println("[LOG] Removed task ID: " + taskId);
        } else {
            System.out.println("[ERROR] Task ID " + taskId + " not found.");
        }
    }

    // --- Helper để tìm Task ---
    public Optional<Task> findTask(String taskId) {
        return tasks.stream().filter(t -> t.getId().equals(taskId)).findFirst();
    }

    // --- In danh sách task ---
    public void printAllTasks() {
        System.out.println("--- Project: " + name + " ---");
        if (tasks.isEmpty()) System.out.println("(No tasks)");
        tasks.forEach(System.out::println);
    }
}
