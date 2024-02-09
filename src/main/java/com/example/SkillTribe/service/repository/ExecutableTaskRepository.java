package com.example.SkillTribe.service.repository;

import com.example.SkillTribe.model.ExecutableTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutableTaskRepository  extends JpaRepository<ExecutableTask, Long> {
}
