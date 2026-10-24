package sg.edu.ntu.taskflow_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.ntu.taskflow_api.model.Task;
import sg.edu.ntu.taskflow_api.repository.TaskRepository;

class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(new TaskRepository());
    }

    @Test
    void markingTaskCompleteDoesNotCreateAnotherRepositoryEntry() {
        Task task = new Task();
        task.setTitle("Test task");
        task.setCompleted(false);
        Task createdTask = taskService.createTask(task);

        Task completedTask = taskService.markTaskAsCompleted(createdTask.getId());

        assertEquals(createdTask.getId(), completedTask.getId());
        assertEquals(1, taskService.findAllTasks().size());
        assertSame(completedTask, taskService.findTaskById(createdTask.getId()));
        assertEquals(true, completedTask.getCompleted());
    }

    @Test
    void updatingTaskPreservesItsIdAndRepositoryEntry() {
        Task task = new Task();
        task.setTitle("Original title");
        task.setCompleted(false);
        Task createdTask = taskService.createTask(task);

        Task changes = new Task();
        changes.setTitle("Updated title");
        changes.setDescription("Updated description");
        changes.setCompleted(true);
        Task updatedTask = taskService.updateTask(createdTask.getId(), changes);

        assertEquals(createdTask.getId(), updatedTask.getId());
        assertEquals(1, taskService.findAllTasks().size());
        assertSame(updatedTask, taskService.findTaskById(createdTask.getId()));
        assertEquals("Updated title", updatedTask.getTitle());
    }

    @Test
    void deletingTaskRemovesItFromRepository() {
        Task task = new Task();
        task.setTitle("Task to delete");
        Task createdTask = taskService.createTask(task);

        assertTrue(taskService.deleteTask(createdTask.getId()));
        assertEquals(0, taskService.findAllTasks().size());
        assertNull(taskService.findTaskById(createdTask.getId()));
        assertFalse(taskService.deleteTask(createdTask.getId()));
    }
}
