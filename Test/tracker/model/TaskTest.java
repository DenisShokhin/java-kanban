package tracker.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task1 = new Task("Task 1", "Desc 1", TaskStatus.NEW);
        task1.setId(1);
        Task task2 = new Task("Task 2", "Desc 2", TaskStatus.IN_PROGRESS);
        task2.setId(1);
        assertEquals(task1, task2, "Задачи с одинаковым id должны быть равны.");
    }

    @Test
    void epicAndSubtaskWithSameIdShouldBeEqual() {
        Epic epic = new Epic("Epic", "Desc");
        epic.setId(5);
        Subtask subtask = new Subtask("Subtask", "Desc", TaskStatus.NEW, 10);
        subtask.setId(5);
        assertEquals(epic, subtask, "Наследники Task с одинаковым id должны быть равны.");
    }
}