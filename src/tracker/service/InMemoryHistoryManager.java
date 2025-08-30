package tracker.service;

import tracker.model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final int MAX_HISTORY_SIZE = 10;
    private final List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task == null) return;
        Task snapshot = task.copy();
        if (history.size() >= MAX_HISTORY_SIZE) {
            history.remove(0);
        }
        history.add(snapshot);
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}