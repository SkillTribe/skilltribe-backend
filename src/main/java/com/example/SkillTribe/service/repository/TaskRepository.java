package com.example.SkillTribe.service.repository;

import com.example.SkillTribe.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
