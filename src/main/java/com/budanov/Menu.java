package com.budanov;

import com.budanov.entity.Task;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu {

    TaskService taskService;
    Scanner scanner = new Scanner(System.in);

    public Menu(TaskService taskService) {
        System.out.println("Создание бина--Меню--");
        this.taskService = taskService;
    }

    public void showMenu() throws SQLException {
        int m;
        do {
            System.out.println("1 - tasks list");
            System.out.println("2 - add task");
            System.out.println("3 - remove");
            System.out.println("4 - set DONE");
            System.out.println("5 - copy");//TODO
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
            System.out.println("Task: " + "| ID :" + task.getId() + " " + "|  Title :       " + task.getTitle() + "|  Description :        " + task.getDescription() + " " + "| is_DONE : " + task.isDone());
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
