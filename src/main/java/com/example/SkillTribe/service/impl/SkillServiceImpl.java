package com.example.SkillTribe.service.impl;

import com.example.SkillTribe.exception.NotFoundException;
import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.service.SkillService;
import com.example.SkillTribe.service.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    @Override
    public List<Skill> getPrerequisiteSkills(Long id) {
        return getById(id).getPrerequisiteSkills().stream().toList();
    }

    @Override
    public Skill getById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new NotFoundException(Skill.class, id));
    }

    @Override
    public Skill createSkill(Skill skill) {
        if(skill.getPrerequisiteSkills() != null && !skill.getPrerequisiteSkills().isEmpty()) {
            Set<Skill> skills = skill.getPrerequisiteSkills().stream().map(s -> getById(s.getId())).collect(Collectors.toSet());
            skill.setPrerequisiteSkills((HashSet)skills);
        }

        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) {
        Skill toUpdate = getById(id);
        toUpdate.update(skill);
        return skillRepository.save(toUpdate);
    }

    @Override
    public void deleteSkill(Skill skill) {
        skillRepository.delete(skill);
    }

    @Override
    public Skill addPrerequisiteSkill(Long id, Skill skill) {
        Skill toUpdate= getById(id);
        toUpdate.getPrerequisiteSkills().add(skill);
        return skillRepository.save(toUpdate);
    }

    @Override
    public Skill removePrerequisiteSkill(Long id, Skill skill) {
        Skill toUpdate= getById(id);
        toUpdate.getPrerequisiteSkills().remove(skill);
        return skillRepository.save(toUpdate);
    }
}
