package com.budanov;

import com.budanov.entity.Task;

import java.util.List;

public interface Repository {

    void delete(int id);

    List<Task> list();

    void save(Task task);

    void setIsDone(int taskId, boolean isDone);
}
