package com.example.SkillTribe.service.impl;

import com.example.SkillTribe.exception.NotFoundException;
import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.guide.Guide;
import com.example.SkillTribe.model.guide.GuidePlan;
import com.example.SkillTribe.model.guide.GuideTask;
import com.example.SkillTribe.service.GuideService;
import com.example.SkillTribe.service.repository.guide.GuideRepository;
import com.example.SkillTribe.service.repository.guide.GuideTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;
    private final GuideTaskRepository guideTaskRepository;

    @Autowired
    public GuideServiceImpl(GuideRepository guideRepository, GuideTaskRepository guideTaskRepository) {
        this.guideRepository = guideRepository;
        this.guideTaskRepository = guideTaskRepository;
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

    @Override
    public Guide addTask(Long guideId, GuideTask guideTask) {
        Guide guide = getById(guideId);
        GuidePlan guidePlan = guide.getGuidePlan();
        //guideTask.setGuidePlan(guidePlan);
        guideTask = guideTaskRepository.save(guideTask);
        return guide;
    }

    @Override
    public Guide removeTask(Long guideId, Long guideTaskId) {
        Guide guide = getById(guideId);
        GuidePlan guidePlan = guide.getGuidePlan();
        GuideTask guideTask = guideTaskRepository.findById(guideId).orElseThrow(() -> new NotFoundException(GuideTask.class, guideTaskId));
        //guideTask.setGuidePlan(null);
        guidePlan.getGuideTasks().remove(guideTask);
        guideTask = guideTaskRepository.save(guideTask);
        return guide;
    }
}
