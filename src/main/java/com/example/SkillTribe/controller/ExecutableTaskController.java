package com.example.SkillTribe.controller;

import com.example.SkillTribe.dto.request.ExecutableTaskRequest;
import com.example.SkillTribe.dto.response.ExecutableTaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/executable-tasks")
public interface ExecutableTaskController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ExecutableTaskResponse> getAll();
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ExecutableTaskResponse getById(@PathVariable Long id);
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ExecutableTaskResponse createExecutableTask(@RequestBody ExecutableTaskRequest task);
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ExecutableTaskResponse updateExecutableTask(@PathVariable Long id, @RequestBody ExecutableTaskRequest task);
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteExecutableTask(@PathVariable Long id);
    @PatchMapping("/{id}/change-complete")
    ExecutableTaskResponse changeComplete(@PathVariable Long id);
}
