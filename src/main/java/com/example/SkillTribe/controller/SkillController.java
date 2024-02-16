package com.example.SkillTribe.controller;

import com.example.SkillTribe.dto.request.SkillRequest;
import com.example.SkillTribe.dto.response.SkillResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/skills")
public interface SkillController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<SkillResponse> getAll();
    @GetMapping("/{id)")
    @ResponseStatus(HttpStatus.OK)
    SkillResponse getById(@PathVariable Long id);
    @GetMapping("/prerequised-skills/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<SkillResponse> getPrerequisedSkills(@PathVariable Long id);
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SkillResponse createSkill(@RequestBody SkillRequest skill);
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    SkillResponse updateSkill(@PathVariable Long id, @RequestBody SkillRequest skill);
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteSkill(@PathVariable Long id);
    @PatchMapping("/{mainId}/add-prerequised-skill/{preId}")
    @ResponseStatus(HttpStatus.OK)
    SkillResponse addPrerequisedSkill(Long mainId, Long preId);
    @PatchMapping("/{mainId}/remove-prerequised-skill/{preId}")
    @ResponseStatus(HttpStatus.OK)
    SkillResponse removePrerequisedSkill(Long id, Long preId);
}
