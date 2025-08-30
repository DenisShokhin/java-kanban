package tracker;

import tracker.service.TaskManager;
import tracker.model.*;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

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

        //Демонстрация истории
        System.out.println("История просмотров:");
        manager.getTaskById(task1.getId());
        System.out.println("История после просмотра task1: " + manager.getHistory());
        manager.getEpicById(epic1.getId());
        System.out.println("История после просмотра epic1: " + manager.getHistory());
        manager.getSubtaskById(subTask1.getId());
        System.out.println("История после просмотра subTask1: " + manager.getHistory());
        manager.getTaskById(task2.getId());
        System.out.println("История после просмотра task2: " + manager.getHistory());
        manager.getSubtaskById(subTask2.getId());
        System.out.println("История после просмотра subTask2: " + manager.getHistory());
        manager.getTaskById(task1.getId());
        System.out.println("История после повторного просмотра task1: " + manager.getHistory());
        System.out.println("Финальная история просмотров:");
        System.out.println(manager.getHistory());
    }
}