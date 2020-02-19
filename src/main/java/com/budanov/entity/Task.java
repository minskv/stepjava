package com.budanov.entity;

/*
entity - классы для хранения данных которые будут
храниться в базе данных
 */
//Типичное ООП  - объект реального мира который мы описываем
//хранит всю суть программы - описывает ЗАДАЧУ

import javax.persistence.*;



@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id; //появится(значит его может НЕ быть=там может быть null) только когда я сохранюсь в базу (потому что база их генерит)

    String title;
    String description;
    boolean isDone; //false

    public Task(int id, String title, String description, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}
