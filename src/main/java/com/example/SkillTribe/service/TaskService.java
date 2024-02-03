package com.example.SkillTribe.service;

import com.example.SkillTribe.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll();
    Task getById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Task task);
    // todo add/remove related skill
}
