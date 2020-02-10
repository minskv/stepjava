package com.budanov;

import com.budanov.entity.Task;

import java.sql.SQLException;
import java.util.List;


/*
Слой бизнес логики, слой сервиса, центральный слой
    промежуточный между "отображением" и "базой  данных"
 */
public class TaskService {

    Repository repository;


    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public List<Task> list() {
        return repository.list();
    }

    public void addNewTask(String title, String description) {
        Task newTask = new Task(title, description);
        repository.save(newTask);
    }

    public void remove(int taskId) {
        repository.delete(taskId);
    }

    public void setDone(int taskId, boolean isDone) {
        repository.setIsDone(taskId, isDone);
    }
}
