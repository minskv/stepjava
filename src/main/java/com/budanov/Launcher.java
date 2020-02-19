package com.budanov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.budanov");

        Menu menu = context.getBean(Menu.class);
        menu.showMenu();
    }


}
