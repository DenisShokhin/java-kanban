package tracker;

import tracker.service.HistoryManager;
import tracker.service.InMemoryHistoryManager;
import tracker.service.TaskManager;
import tracker.service.InMemoryTaskManager;

public final class Managers {

    private Managers() {
    }

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}