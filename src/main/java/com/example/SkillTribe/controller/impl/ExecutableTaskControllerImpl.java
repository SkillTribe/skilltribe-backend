package com.example.SkillTribe.controller.impl;

import com.example.SkillTribe.controller.ExecutableTaskController;
import com.example.SkillTribe.dto.request.ExecutableTaskRequest;
import com.example.SkillTribe.dto.response.ExecutableTaskResponse;
import com.example.SkillTribe.service.ExecutableTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class ExecutableTaskControllerImpl implements ExecutableTaskController {
    private final ExecutableTaskService executableTaskService;
    @Autowired
    public ExecutableTaskControllerImpl(ExecutableTaskService executableTaskService) {
        this.executableTaskService = executableTaskService;
    }

    @Override
    public List<ExecutableTaskResponse> getAll() {
        return executableTaskService.getAll().stream().map(ExecutableTaskResponse::new).collect(Collectors.toList());
    }

    @Override
    public ExecutableTaskResponse getById(Long id) {
        return new ExecutableTaskResponse(executableTaskService.getById(id));
    }

    @Override
    public ExecutableTaskResponse createExecutableTask(ExecutableTaskRequest task) {
        return new ExecutableTaskResponse(task.toModel());
    }

    @Override
    public ExecutableTaskResponse updateExecutableTask(Long id, ExecutableTaskRequest task) {
        return new ExecutableTaskResponse(executableTaskService.updateExecutableTask(id, task.toModel()));
    }

    @Override
    public void deleteExecutableTask(Long id) {
        executableTaskService.deleteExecutableTask(executableTaskService.getById(id));
    }

    @Override
    public ExecutableTaskResponse changeComplete(Long id) {
        return new ExecutableTaskResponse(executableTaskService.changeComplete(executableTaskService.getById(id)));
    }
}
