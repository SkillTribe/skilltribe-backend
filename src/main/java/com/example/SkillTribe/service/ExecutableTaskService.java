package com.example.SkillTribe.service;

import com.example.SkillTribe.model.ExecutableTask;
import com.example.SkillTribe.model.Task;

import java.util.List;

public interface ExecutableTaskService {
    List<ExecutableTask> getAll();
    ExecutableTask getById(Long id);
    ExecutableTask createExecutableTask(ExecutableTask task);
    ExecutableTask updateExecutableTask(Long id, ExecutableTask task);
    void deleteExecutableTask(ExecutableTask task);
    ExecutableTask changeComplete(ExecutableTask task);
}
