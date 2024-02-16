package com.example.SkillTribe.controller.impl;

import com.example.SkillTribe.controller.SkillController;
import com.example.SkillTribe.dto.request.SkillRequest;
import com.example.SkillTribe.dto.response.SkillResponse;
import com.example.SkillTribe.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class SkillControllerImpl implements SkillController {
    private final SkillService skillService;
    @Autowired
    public SkillControllerImpl(SkillService skillService) {
        this.skillService = skillService;
    }

    @Override
    public List<SkillResponse> getAll() {
        return skillService.getAll().stream().map(SkillResponse::new).collect(Collectors.toList());
    }

    @Override
    public SkillResponse getById(Long id) {
        return new SkillResponse(skillService.getById(id));
    }

    @Override
    public List<SkillResponse> getPrerequisedSkills(Long id) {
        return skillService.getPrerequisedSkills(id).stream().map(SkillResponse::new).collect(Collectors.toList());
    }

    @Override
    public SkillResponse createSkill(SkillRequest skill) {
        return new SkillResponse(skillService.createSkill(skill.toModel()));
    }

    @Override
    public SkillResponse updateSkill(Long id, SkillRequest skill) {
        return new SkillResponse(skillService.updateSkill(id, skill.toModel()));
    }

    @Override
    public void deleteSkill(Long id) {
        skillService.deleteSkill(skillService.getById(id));
    }

    @Override
    public SkillResponse addPrerequisedSkill(Long mainId, Long preId) {
        return new SkillResponse(skillService.addPrerequisedSkill(mainId, skillService.getById(preId)));
    }

    @Override
    public SkillResponse removePrerequisedSkill(Long id, Long preId) {
        return new SkillResponse(skillService.removePrerequisedSkill(id, skillService.getById(preId)));
    }
}
