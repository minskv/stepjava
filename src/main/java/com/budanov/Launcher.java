package com.budanov;

import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        TaskService taskService = new TaskService();

        Repository taskRepository = new TaskRepository();

        Repository taskRepositoryRAM = new TaskRepositoryRAM();

        menu.setTaskService(taskService);
        taskService.setRepository(taskRepository);

        menu.setTaskService(taskService);
        taskService.setRepository(taskRepositoryRAM);

        menu.showMenu();
    }


}
