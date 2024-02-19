package com.example.SkillTribe.controller.impl;

import com.example.SkillTribe.controller.GuideTaskController;
import com.example.SkillTribe.dto.request.GuideTaskRequest;
import com.example.SkillTribe.dto.response.GuideTaskResponse;
import com.example.SkillTribe.service.SkillService;
import com.example.SkillTribe.service.GuideTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class GuideTaskControllerImpl implements GuideTaskController {
    private final GuideTaskService guideTaskService;
    private final SkillService skillService;
    @Autowired
    public GuideTaskControllerImpl(GuideTaskService guideTaskService, SkillService skillService) {
        this.guideTaskService = guideTaskService;
        this.skillService = skillService;
    }

    @Override
    public List<GuideTaskResponse> getAll() {
        return guideTaskService.getAll().stream().map(GuideTaskResponse::new).collect(Collectors.toList());
    }

    @Override
    public GuideTaskResponse getById(Long id) {
        return new GuideTaskResponse(guideTaskService.getById(id));
    }

    @Override
    public GuideTaskResponse createTask(GuideTaskRequest task) {
        return new GuideTaskResponse(guideTaskService.createGuideTask(task.toModel()));
    }

    @Override
    public GuideTaskResponse updateTask(Long id, GuideTaskRequest task) {
        return new GuideTaskResponse(guideTaskService.updateGuideTask(id, task.toModel()));
    }

    @Override
    public void deleteTask(Long taskId) {
        guideTaskService.deleteGuideTask(guideTaskService.getById(taskId));
    }

    @Override
    public GuideTaskResponse addRelatedSkill(Long taskId, Long skillId) {
        return new GuideTaskResponse(guideTaskService.addRelatedSkill(guideTaskService.getById(taskId), skillService.getById(skillId)));
    }

    @Override
    public GuideTaskResponse removeRelatedSkill(Long taskId, Long skillId) {
        return new GuideTaskResponse(guideTaskService.removeRelatedSkill(guideTaskService.getById(taskId), skillService.getById(skillId)));
    }
}
