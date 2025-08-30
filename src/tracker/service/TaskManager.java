package tracker.service;

import tracker.model.*;
import java.util.List;

public interface TaskManager {
    void addTask(Task task);
    List<Task> getAllTasks();
    void deleteAllTasks();
    Task getTaskById(int id);
    void updateTask(Task task);
    void deleteTaskById(int id);

    void addEpic(Epic epic);
    List<Epic> getAllEpics();
    void deleteAllEpics();
    Epic getEpicById(int id);
    void updateEpic(Epic epic);
    void deleteEpicById(int id);

    void addSubtask(Subtask subtask);
    List<Subtask> getAllSubtasks();
    void deleteAllSubtasks();
    Subtask getSubtaskById(int id);
    void updateSubtask(Subtask subtask);
    void deleteSubtaskById(int id);
    List<Subtask> getSubtasksOfEpic(int epicId);

    List<Task> getHistory();
}