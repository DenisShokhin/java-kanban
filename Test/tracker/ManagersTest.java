package tracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    @Test
    void getDefaultShouldReturnInitializedTaskManager() {
        assertNotNull(Managers.getDefault(), "TaskManager не должен быть null.");
    }

    @Test
    void getDefaultHistoryShouldReturnInitializedHistoryManager() {
        assertNotNull(Managers.getDefaultHistory(), "HistoryManager не должен быть null.");
    }

}