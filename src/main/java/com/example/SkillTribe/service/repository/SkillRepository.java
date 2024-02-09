package com.example.SkillTribe.service.repository;

import com.example.SkillTribe.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
