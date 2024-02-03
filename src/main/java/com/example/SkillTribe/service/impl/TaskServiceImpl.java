package com.example.SkillTribe.service.impl;

import com.example.SkillTribe.exception.NotFoundException;
import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.Task;
import com.example.SkillTribe.service.TaskService;
import com.example.SkillTribe.service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException(Task.class, id));
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task toUpdate = getById(id);
        toUpdate.update(task);
        return taskRepository.save(toUpdate);
    }

    @Override
    public void deleteTask(Task task) {
        task.setRelatedSkill(new HashSet<>());
        taskRepository.save(task);
        taskRepository.delete(task);
    }
}
