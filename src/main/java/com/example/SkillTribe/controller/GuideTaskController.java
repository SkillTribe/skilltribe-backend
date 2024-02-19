package com.example.SkillTribe.controller;

import com.example.SkillTribe.dto.request.GuideTaskRequest;
import com.example.SkillTribe.dto.response.GuideTaskResponse;
import com.example.SkillTribe.dto.response.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/tasks")
public interface GuideTaskController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<GuideTaskResponse> getAll();
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    GuideTaskResponse getById(@PathVariable Long id);
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    GuideTaskResponse createTask(@RequestBody GuideTaskRequest task);
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    GuideTaskResponse updateTask(@PathVariable Long id, @RequestBody GuideTaskRequest task);
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(@PathVariable Long taskId);
    @PatchMapping("/{taskId}/add-related-skill/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    GuideTaskResponse addRelatedSkill(Long taskId, Long skillId);
    @PatchMapping("/{taskId}/remove-related-skill/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    GuideTaskResponse removeRelatedSkill(Long taskId, Long skillId);
}
