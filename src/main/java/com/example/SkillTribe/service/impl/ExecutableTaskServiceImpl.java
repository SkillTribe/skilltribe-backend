package com.example.SkillTribe.service.impl;

import com.example.SkillTribe.exception.NotFoundException;
import com.example.SkillTribe.model.ExecutableTask;
import com.example.SkillTribe.model.Task;
import com.example.SkillTribe.service.ExecutableTaskService;
import com.example.SkillTribe.service.repository.ExecutableTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ExecutableTaskServiceImpl implements ExecutableTaskService {
    private final ExecutableTaskRepository executableTaskRepository;

    @Autowired
    public ExecutableTaskServiceImpl(ExecutableTaskRepository executableTaskRepository) {
        this.executableTaskRepository = executableTaskRepository;
    }

    @Override
    public List<ExecutableTask> getAll() {
        return executableTaskRepository.findAll();
    }

    @Override
    public ExecutableTask getById(Long id ) {
        return executableTaskRepository.findById(id).orElseThrow(() -> new NotFoundException(ExecutableTask.class, id));
    }

    @Override
    public ExecutableTask createExecutableTask(ExecutableTask task) {
        return executableTaskRepository.save(task);
    }

    @Override
    public ExecutableTask updateExecutableTask(Long id, ExecutableTask task) {
        ExecutableTask toUpdate = getById(id);
        toUpdate.update(task);
        return executableTaskRepository.save(toUpdate);
    }

    @Override
    public void deleteExecutableTask(ExecutableTask task) {
        executableTaskRepository.delete(task);
    }

    @Override
    public ExecutableTask changeComplete(ExecutableTask task) {
        task.setIsComplete(!task.getIsComplete());
        return executableTaskRepository.save(task);
    }
}
