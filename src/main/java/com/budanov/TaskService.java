package com.budanov;

import com.budanov.entity.Task;

import java.sql.SQLException;
import java.util.List;


/*
Слой бизнес логики, слой сервиса, центральный слой
    промежуточный между "отображением" и "базой  данных"
 */
public class TaskService {

    TaskRepository taskRepository = new TaskRepository();

    public List<Task> list() throws SQLException {
        return taskRepository.list();
    }

    public void addNewTask(String title, String description) throws SQLException {
        Task newTask = new Task(title, description);
        taskRepository.save(newTask);
    }

    public void remove(int taskId) throws SQLException {
        taskRepository.delete(taskId);
    }

    public void setDone(int taskId, boolean isDone) throws SQLException {
        taskRepository.setIsDone(taskId, isDone);
    }

    public void viewInAsc(String columnName) throws SQLException  {
        taskRepository.viewInAsc(columnName);
    }
}
