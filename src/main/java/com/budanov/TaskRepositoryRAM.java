package com.budanov;

import com.budanov.entity.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepositoryRAM implements Repository {
    Map<Integer, Task> tasks = new HashMap<>();
    Integer currentID = 0;

    @Override
    public void delete(int taskId) {
        tasks.remove(taskId);
    }

    @Override
    public List<Task> list() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void save(Task task) {
        if (task.getId() == null)
            task.setId(currentID++);
        tasks.put(task.getId(), task);

    }

    @Override
    public void setIsDone(int taskId, boolean isDone) {
        Task task = tasks.get(taskId);
        if (task != null)
            task.setDone(isDone);
    }
}
