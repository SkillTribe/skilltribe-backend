package com.example.SkillTribe.controller;

import com.example.SkillTribe.dto.request.GuideRequest;
import com.example.SkillTribe.dto.request.GuideTaskRequest;
import com.example.SkillTribe.dto.response.GuideResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/guides")
public interface GuideController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<GuideResponse> getAll();
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    GuideResponse getById(@PathVariable Long id);
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GuideResponse createGuide(@RequestBody GuideRequest guide);
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    GuideResponse updateGuide(@PathVariable Long id, @RequestBody GuideRequest guide);
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteGuide(@RequestBody Long id);
    @PatchMapping("/{guideId}/add-related-skill/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    GuideResponse addRelatedSkill(Long id, Long skillId);
    @PatchMapping("/{guideId}/remove-related-skill/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    GuideResponse removeRelatedSkill(Long id, Long skillId);
    @PatchMapping("/{guideId}/add-related-guide-task")
    @ResponseStatus(HttpStatus.OK)
    GuideResponse addTask(Long guideId, GuideTaskRequest guideTask);
    @PatchMapping("/{guideId}/add-related-guide-task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    GuideResponse removeTask(Long guideId, Long guideTaskId);
}
