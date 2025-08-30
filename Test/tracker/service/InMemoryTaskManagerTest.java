package tracker.service;

import tracker.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class InMemoryTaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    void addTaskAndGetById() {
        Task task = new Task("Test Task", "Desc", TaskStatus.NEW);
        taskManager.addTask(task);
        final int taskId = task.getId();

        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getAllTasks();
        assertNotNull(tasks, "Список задач не должен быть null.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи в списке не совпадают.");
    }

    @Test
    void taskShouldBeUnchangedWhenAddedToManager() {
        Task task = new Task("Name", "Description", TaskStatus.NEW);
        taskManager.addTask(task);
        Task savedTask = taskManager.getTaskById(task.getId());

        assertEquals(task.getName(), savedTask.getName());
        assertEquals(task.getDescription(), savedTask.getDescription());
        assertEquals(task.getStatus(), savedTask.getStatus());
    }

    @Test
    void idsShouldNotConflict() {
        Task taskWithManualId = new Task("Manual ID", "Desc", TaskStatus.NEW);
        taskWithManualId.setId(100);
        taskManager.addTask(taskWithManualId);

        Task taskWithGeneratedId = new Task("Generated ID", "Desc", TaskStatus.NEW);
        taskManager.addTask(taskWithGeneratedId);

        assertNotEquals(taskWithManualId.getId(), taskWithGeneratedId.getId());
        assertTrue(taskWithManualId.getId() > 0);
        assertTrue(taskWithGeneratedId.getId() > 0);
    }

    @Test
    void epicCannotBeItsOwnSubtask() {
        Epic epic = new Epic("Epic", "Desc");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Sub", "Desc", TaskStatus.NEW, epic.getId());
        taskManager.addSubtask(subtask);

        assertFalse(epic.getSubtaskIds().contains(epic.getId()), "Эпик не должен содержать себя как подзадачу.");
    }

    @Test
    void subtaskCannotBecomeItsOwnEpic() {
        Epic epic1 = new Epic("Epic 1", "Desc");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask("Sub 1", "Desc", TaskStatus.NEW, epic1.getId());
        taskManager.addSubtask(subtask1);

        assertNotEquals(subtask1.getId(), subtask1.getEpicId());
    }

    @Test
    void historyManagerSavesPreviousTaskVersion() {
        Task task = new Task("Task 1", "Desc 1", TaskStatus.NEW);
        taskManager.addTask(task);

        taskManager.getTaskById(task.getId());

        task.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task);

        taskManager.getTaskById(task.getId());

        List<Task> history = taskManager.getHistory();

        assertEquals(2, history.size());
        assertEquals(TaskStatus.NEW, history.get(0).getStatus(), "В истории должна быть версия со статусом NEW.");
        assertEquals(TaskStatus.IN_PROGRESS, history.get(1).getStatus(), "В истории должна быть версия со статусом IN_PROGRESS.");
    }

    @Test
    void deletingEpicDeletesItsSubtasks() {
        Epic epic = new Epic("Epic", "Desc");
        taskManager.addEpic(epic);
        Subtask subtask = new Subtask("Sub", "Desc", TaskStatus.NEW, epic.getId());
        taskManager.addSubtask(subtask);

        assertEquals(1, taskManager.getAllEpics().size());
        assertEquals(1, taskManager.getAllSubtasks().size());

        taskManager.deleteEpicById(epic.getId());

        assertEquals(0, taskManager.getAllEpics().size());
        assertEquals(0, taskManager.getAllSubtasks().size());
        assertNull(taskManager.getSubtaskById(subtask.getId()));
    }
}