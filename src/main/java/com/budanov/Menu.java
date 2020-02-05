package com.budanov;

import com.budanov.entity.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


//Слой "представления"-- слой внешний вид --  слой общения с пользователем
/*
Взаимодействие с пользователем
- вывод данных
- ввод данных
- меню
 */
public class Menu {

    /*
    Найти задачу задачу по айди
    (пользователь вводит айди задачи которую он хочу скопировать)
    найти по этому айди старую задачу
    взять её тайтл и дескри
    сделайть НОВУЮ с такиж ЗАГОЛОВОКОМ И ТЕЛОМ
    и сохранить(у неё(у копии)) будет новый айди)
     */
    TaskService taskService = new TaskService();
    Scanner scanner = new Scanner(System.in);

    public void showMenu() throws SQLException {
        int m;
        do {
            System.out.println("1 - tasks list");
            System.out.println("2 - add task");
            System.out.println("3 - remove");
            System.out.println("4 - set DONE");
            System.out.println("5 - copy"); //TODO ДОМОЙ
            System.out.println("9 - exit");
            m = scanner.nextInt();
            scanner.nextLine();
            switch (m) {
                case 1:
                    list();
                    break;
                case 2:
                    addNewTask();
                    break;
                case 3:
                    remove();
                    break;
                case 4:
                    setDone();
                    break;
            }
        } while (m != 9);
    }

    private void list() throws SQLException {
        List<Task> tasks = taskService.list();
        for (Task task : tasks)
            System.out.println("task: " + task.isDone() + " " + task.getId() + "  " + task.getTitle());
    }

    private void addNewTask() throws SQLException {
        System.out.println("Insert new task title");
        String title = scanner.nextLine();
        System.out.println("Insert new task description");
        String description = scanner.nextLine();
        taskService.addNewTask(title, description);
    }

    private void remove() throws SQLException {
        System.out.println("Enter task id to remove");
        int taskId = scanner.nextInt();
        scanner.nextLine();
        taskService.remove(taskId);
    }

    private void setDone() throws SQLException {
        System.out.println("Enter task id to mark done");
        int taskId = scanner.nextInt();
        scanner.nextLine();
        taskService.setDone(taskId, true);
    }
}