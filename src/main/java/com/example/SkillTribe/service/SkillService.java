package com.example.SkillTribe.service;

import com.example.SkillTribe.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> getAll();
    List<Skill> getPrerequisiteSkills(Long id);
    Skill getById(Long id);
    Skill createSkill(Skill skill);
    Skill updateSkill(Long id, Skill skill);
    void deleteSkill(Skill skill);

    Skill addPrerequisiteSkill(Long id, Skill skill);
    Skill removePrerequisiteSkill(Long id, Skill skill);
}
