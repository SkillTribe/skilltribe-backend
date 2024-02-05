package com.example.SkillTribe.service.impl;

import com.example.SkillTribe.exception.NotFoundException;
import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.guide.Guide;
import com.example.SkillTribe.model.guide.GuideTask;
import com.example.SkillTribe.service.GuideService;
import com.example.SkillTribe.service.SkillService;
import com.example.SkillTribe.service.repository.guide.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;

    @Autowired
    public GuideServiceImpl(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    @Override
    public List<Guide> getAll() {
        return guideRepository.findAll();
    }

    @Override
    public Guide getById(Long id) {
        return guideRepository.findById(id).orElseThrow(() -> new NotFoundException(Guide.class, id));
    }

    @Override
    public Guide createGuide(Guide guide) {
        return guideRepository.save(guide);
    }

    @Override
    public Guide updateGuide(Long id, Guide guide) {
        Guide toUpdate = getById(id);
        toUpdate.update(guide);
        return guideRepository.save(guide);
    }

    @Override
    public void deleteGuide(Guide guide) {
        //todo cascade delete
        guideRepository.delete(guide);
    }

    @Override
    public Guide addRelatedSkill(Long id, Skill skill) {
        Guide toUpdate = getById(id);
        toUpdate.getSkills().add(skill);
        return guideRepository.save(toUpdate);
    }

    @Override
    public Guide removeRelatedSkill(Long id, Skill skill) {
        Guide toUpdate = getById(id);
        toUpdate.getSkills().remove(skill);
        return guideRepository.save(toUpdate);
    }

    /*@Override
    public Guide addTask(GuideTask guideTask) {
        return null;
    }

    @Override
    public Guide removeTask(GuideTask guideTask) {
        return null;
    }*/
}
