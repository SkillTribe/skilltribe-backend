package com.example.SkillTribe.controller.impl;

import com.example.SkillTribe.controller.GuideController;
import com.example.SkillTribe.dto.request.GuideRequest;
import com.example.SkillTribe.dto.request.GuideTaskRequest;
import com.example.SkillTribe.dto.response.GuideResponse;
import com.example.SkillTribe.service.GuideService;
import com.example.SkillTribe.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class GuideControllerImpl implements GuideController {
    private final GuideService guideService;
    private final SkillService skillService;

    @Autowired
    public GuideControllerImpl(GuideService guideService, SkillService skillService) {
        this.guideService = guideService;
        this.skillService = skillService;
    }

    @Override
    public List<GuideResponse> getAll() {
        return guideService.getAll().stream().map(GuideResponse::new).collect(Collectors.toList());
    }

    @Override
    public GuideResponse getById(Long id) {
        return new GuideResponse(guideService.getById(id));
    }

    @Override
    public GuideResponse createGuide(GuideRequest guide) {
        return new GuideResponse(guideService.createGuide(guide.toModel()));
    }

    @Override
    public GuideResponse updateGuide(Long id, GuideRequest guide) {
        return new GuideResponse(guideService.updateGuide(id, guide.toModel()));
    }

    @Override
    public void deleteGuide(Long id) {
        guideService.deleteGuide(guideService.getById(id));
    }

    @Override
    public GuideResponse addRelatedSkill(Long id, Long skillId) {
        return new GuideResponse(guideService.addRelatedSkill(id, skillService.getById(id)));
    }

    @Override
    public GuideResponse removeRelatedSkill(Long id, Long skillId) {
        return new GuideResponse(guideService.removeRelatedSkill(id, skillService.getById(id)));
    }

    @Override
    public GuideResponse addTask(Long guideId, GuideTaskRequest guideTask) {
        return new GuideResponse(guideService.addTask(guideId, guideTask.toModel()));
    }

    @Override
    public GuideResponse removeTask(Long guideId, Long guideTaskId) {
        return new GuideResponse(guideService.removeTask(guideId, guideTaskId));
    }
}
