package ikus.service;

import ikus.entity.Project;
import ikus.entity.Task;
import ikus.repository.ProjectRepository;
import ikus.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ikus.repository.UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    private Task taskInput;
    private Project projectInput;

    @BeforeEach
    void initData() {

        projectInput = new Project();
        projectInput.setId("PRJ-01");
        projectInput.setName("Test Project");

        taskInput = new Task();
        taskInput.setTitle("Test Task");
    }

    @Test
    void createTask_Success() {

        Mockito.when(projectRepository.findById("PRJ-01")).thenReturn(Optional.of(projectInput));

        Mockito.when(taskRepository.save(any(Task.class))).thenReturn(taskInput);

        Task result = taskService.createTask(taskInput, "PRJ-01");

        Assertions.assertNotNull(result);

        Assertions.assertEquals("PRJ-01", result.getProject().getId());
    }

    @Test
    void createTask_Fail_ProjectNotFound() {

        Mockito.when(projectRepository.findById("PRJ-99")).thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(RuntimeException.class,
                () -> taskService.createTask(taskInput, "PRJ-99")
        );

        Assertions.assertTrue(exception.getMessage().contains("không tồn tại"));
    }

    @Test
    void assignTask_Success() {

        ikus.entity.User user = new ikus.entity.User();
        user.setId("U1");
        user.setFullName("Nhan Vien A");

        Mockito.when(taskRepository.findById("T-01")).thenReturn(Optional.of(taskInput));
        Mockito.when(userRepository.findById("U1")).thenReturn(Optional.of(user));
        Mockito.when(taskRepository.save(any(Task.class))).thenReturn(taskInput);

        Task result = taskService.assignTask("T-01", "U1");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("U1", result.getAssignee().getId());
    }
}
