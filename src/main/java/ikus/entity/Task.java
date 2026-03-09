package ikus.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Task {
    private String id;

    private String title;

    public enum TaskStatus {
        OPEN, IN_PROGRESS, REVIEW, DONE, CANCELLED
    }
    private TaskStatus status;

    public enum TaskPriority {
        LOW, MEDIUM, HIGH
    }
    private TaskPriority priority;

    private LocalDate dueDate;

    private User assignee;

    public Task(String id, String title, TaskPriority priority, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = TaskStatus.OPEN;
    }

    @Override
    public String toString() {
        return String.format("entity.ikus.Task[ID=%s, Title=%s, Status=%s, Assignee=%s]",
                id, title, status, (assignee != null ? assignee.getFullName() : "Unassigned"));
    }
}
