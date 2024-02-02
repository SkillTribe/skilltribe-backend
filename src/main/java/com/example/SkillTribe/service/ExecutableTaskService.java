package com.example.SkillTribe.service;

import com.example.SkillTribe.model.ExecutableTask;

import java.util.List;

public interface ExecutableTaskService {
    List<ExecutableTask> getAll();
    ExecutableTask getById();
    ExecutableTask createExecutableTask(ExecutableTask task);
    ExecutableTask updateExecutableTask(Long id, ExecutableTask task);
    void deleteExecutableTask(ExecutableTask task);
}
