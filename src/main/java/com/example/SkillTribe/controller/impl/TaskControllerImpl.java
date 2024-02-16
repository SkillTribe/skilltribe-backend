package com.example.SkillTribe.controller.impl;

import com.example.SkillTribe.controller.TaskController;
import com.example.SkillTribe.dto.request.GuideTaskRequest;
import com.example.SkillTribe.dto.request.TaskRequest;
import com.example.SkillTribe.dto.response.TaskResponse;
import com.example.SkillTribe.service.SkillService;
import com.example.SkillTribe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class TaskControllerImpl implements TaskController {
    private final TaskService taskService;
    private final SkillService skillService;
    @Autowired
    public TaskControllerImpl(TaskService taskService, SkillService skillService) {
        this.taskService = taskService;
        this.skillService = skillService;
    }

    @Override
    public List<TaskResponse> getAll() {
        return taskService.getAll().stream().map(TaskResponse::new).collect(Collectors.toList());
    }

    @Override
    public TaskResponse getById(Long id) {
        return new TaskResponse(taskService.getById(id));
    }

    @Override
    public TaskResponse createTask(GuideTaskRequest task) {
        return new TaskResponse(taskService.createTask(task.toModel()));
    }

    @Override
    public TaskResponse updateTask(Long id, GuideTaskRequest task) {
        return new TaskResponse(taskService.updateTask(id, task.toModel()));
    }

    @Override
    public void deleteTask(Long taskId) {
        taskService.deleteTask(taskService.getById(taskId));
    }

    @Override
    public TaskResponse addRelatedSkill(Long taskId, Long skillId) {
        return new TaskResponse(taskService.addRelatedSkill(taskService.getById(taskId), skillService.getById(skillId)));
    }

    @Override
    public TaskResponse removeRelatedSkill(Long taskId, Long skillId) {
        return new TaskResponse(taskService.removeRelatedSkill(taskService.getById(taskId), skillService.getById(skillId)));
    }
}
