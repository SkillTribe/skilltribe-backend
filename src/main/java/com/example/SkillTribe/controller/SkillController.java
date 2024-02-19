package com.example.SkillTribe.controller;

import com.example.SkillTribe.dto.request.SkillRequest;
import com.example.SkillTribe.dto.response.SkillResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("skills")
public interface SkillController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<SkillResponse> getAll();
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    SkillResponse getById(@PathVariable Long id);
    @GetMapping("prerequisite-skills/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<SkillResponse> getPrerequisiteSkills(@PathVariable Long id);
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SkillResponse createSkill(@RequestBody SkillRequest skill);
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    SkillResponse updateSkill(@PathVariable Long id, @RequestBody SkillRequest skill);
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteSkill(@PathVariable Long id);
    @PatchMapping("{mainId}/add-prerequisite-skill/{preId}")
    @ResponseStatus(HttpStatus.OK)
    SkillResponse addPrerequisiteSkill(@PathVariable Long mainId, @PathVariable Long preId);
    @PatchMapping("{mainId}/remove-prerequisite-skill/{preId}")
    @ResponseStatus(HttpStatus.OK)
    SkillResponse removePrerequisiteSkill(@PathVariable Long mainId, @PathVariable Long preId);
}
