package com.example.SkillTribe.service;

import com.example.SkillTribe.model.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> getAll();
    List<Skill> getPrerequisedSkills(Long id);
    Skill getById(Long id);
    Skill createSkill(Skill skill);
    Skill updateSkill(Long id, Skill skill);
    void deleteSkill(Skill skill);

    Skill addPrerequisedSkill(Long id, Skill skill);
    Skill removePrerequisedSkill(Long id, Skill skill);
}
