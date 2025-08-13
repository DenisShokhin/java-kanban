package tracker;

import tracker.service.TaskManager;
import tracker.model.*;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создаём 2 задачи
        Task task1 = new Task("Переезд", "Собрать коробки", TaskStatus.NEW);
        Task task2 = new Task("Работа", "Закончить проект", TaskStatus.IN_PROGRESS);
        manager.addTask(task1);
        manager.addTask(task2);

        // Создаём эпик с двумя подзадачами
        Epic epic1 = new Epic("Ремонт", "Капитальный ремонт гаража");
        manager.addEpic(epic1);

        Subtask subTask1 = new Subtask("Купить материалы", "Цемент, краска", TaskStatus.NEW, epic1.getId());
        Subtask subTask2 = new Subtask("Нанять рабочих", "Бригада из 2 человек", TaskStatus.NEW, epic1.getId());
        manager.addSubtask(subTask1);
        manager.addSubtask(subTask2);

        // Создаём эпик с одной подзадачей
        Epic epic2 = new Epic("Подготовка к отпуску", "Собрать чемодан");
        manager.addEpic(epic2);

        Subtask subTask3 = new Subtask("Купить билеты", "2 билета на самолёт в Амстердам", TaskStatus.DONE, epic2.getId());
        manager.addSubtask(subTask3);

        // Печатаем списки задач, эпиков и подзадач
        System.out.println("Все задачи: " + manager.getAllTasks());
        System.out.println("Все эпики: " + manager.getAllEpics());
        System.out.println("Все подзадачи: " + manager.getAllSubtasks());

        // Меняем статусы и смотрим как пересчитались эпики
        task1.setStatus(TaskStatus.IN_PROGRESS);
        task2.setStatus(TaskStatus.DONE);
        subTask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subTask1);
        subTask2.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subTask2);
        subTask3.setStatus(TaskStatus.IN_PROGRESS);
        manager.updateSubtask(subTask3);

        System.out.println("После изменения статусов:");
        System.out.println("Все задачи: " + manager.getAllTasks());
        System.out.println("Все эпики: " + manager.getAllEpics());
        System.out.println("Все подзадачи: " + manager.getAllSubtasks());

        // Удаляем задачу и эпик
        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic2.getId());

        System.out.println("После удаления:");
        System.out.println("Все задачи: " + manager.getAllTasks());
        System.out.println("Все эпики: " + manager.getAllEpics());
    }
}