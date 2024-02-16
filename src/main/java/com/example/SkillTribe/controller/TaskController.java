package com.example.SkillTribe.controller;

import com.example.SkillTribe.dto.request.GuideTaskRequest;
import com.example.SkillTribe.dto.request.TaskRequest;
import com.example.SkillTribe.dto.response.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/tasks")
public interface TaskController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TaskResponse> getAll();
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse getById(@PathVariable Long id);
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    TaskResponse createTask(@RequestBody GuideTaskRequest task);
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse updateTask(@PathVariable Long id, @RequestBody GuideTaskRequest task);
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(@PathVariable Long taskId);
    @PatchMapping("/{taskId}/add-related-skill/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse addRelatedSkill(Long taskId, Long skillId);
    @PatchMapping("/{taskId}/remove-related-skill/{skillId}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse removeRelatedSkill(Long taskId, Long skillId);
}
