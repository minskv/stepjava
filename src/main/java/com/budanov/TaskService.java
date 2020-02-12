package com.budanov;

import com.budanov.entity.Task;
import org.springframework.stereotype.Component;

import java.util.List;


/*
Слой бизнес логики, слой сервиса, центральный слой
    промежуточный между "отображением" и "базой  данных"
 */
@Component
public class TaskService {

    Repository repository;

    public TaskService(Repository repository) {
        System.out.println("Создание Таск Сервиса");
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
