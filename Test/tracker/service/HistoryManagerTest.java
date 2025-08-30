package tracker.service;

import tracker.model.Task;
import tracker.model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class HistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void addShouldAddTaskToHistory() {
        Task task = new Task("Test Task", "Desc", TaskStatus.NEW);
        task.setId(1);
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История не должна быть null.");
        assertEquals(1, history.size(), "Размер истории должен быть 1.");
        assertEquals(task, history.get(0), "Добавленная задача должна быть в истории.");
    }

    @Test
    void historyShouldNotExceedMaxSize() {
        for (int i = 1; i <= 11; i++) {
            Task task = new Task("Task " + i, "Desc", TaskStatus.NEW);
            task.setId(i);
            historyManager.add(task);
        }
        final List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size(), "Размер истории не должен превышать 10.");
        assertEquals(2, history.get(0).getId(), "Самый старый элемент должен быть удален.");
    }

    @Test
    void shouldAllowDuplicatesInHistory() {
        Task task = new Task("Test Task", "Desc", TaskStatus.NEW);
        task.setId(1);
        historyManager.add(task);
        historyManager.add(task);
        final List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size(), "История должна содержать дубликаты.");
    }
}