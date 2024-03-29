package com.example.SkillTribe.service.impl;

import com.example.SkillTribe.exception.NotFoundException;
import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.guide.GuidePlan;
import com.example.SkillTribe.model.guide.GuideTask;
import com.example.SkillTribe.service.GuideTaskService;
import com.example.SkillTribe.service.repository.guide.GuidePlanRepository;
import com.example.SkillTribe.service.repository.guide.GuideTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
class GuideTaskServiceImpl implements GuideTaskService {
    private final GuideTaskRepository guideTaskRepository;
    private final GuidePlanRepository guidePlanRepository;
    @Autowired
    public GuideTaskServiceImpl(GuideTaskRepository guideTaskRepository, GuidePlanRepository guidePlanRepository) {
        this.guideTaskRepository = guideTaskRepository;
        this.guidePlanRepository = guidePlanRepository;
    }

    @Override
    public List<GuideTask> getAll() {
        return guideTaskRepository.findAll();
    }

    @Override
    public List<GuideTask> getAllByGuideId(Long id) {
        return guideTaskRepository.findAllByGuidePlanGuideId(id);
    }

    @Override
    public GuideTask getById(Long id) {
        return guideTaskRepository.findById(id).orElseThrow(() -> new NotFoundException(GuideTask.class, id));
    }

    @Override
    public GuideTask createGuideTask(GuideTask task) {
        return guideTaskRepository.save(task);
    }

    @Override
    public GuideTask updateGuideTask(Long id, GuideTask task) {
        GuideTask toUpdate = getById(id);
        toUpdate.update(task);
        return guideTaskRepository.save(toUpdate);
    }

    @Override
    public void deleteGuideTask(GuideTask task) {
        //task.setRelatedSkill(new HashSet<>());
        GuidePlan guidePlan = task.getGuidePlan();
        task.setGuidePlan(null);
        guidePlan.getGuideTasks().remove(task);
        guidePlanRepository.save(guidePlan);
        guideTaskRepository.delete(task);
    }


    /*@Override
    public GuideTask addRelatedSkill(GuideTask task, Skill skill) {
        //task.getRelatedSkill().add(skill);
        return guideTaskRepository.save(task);
    }

    @Override
    public GuideTask removeRelatedSkill(GuideTask task, Skill skill) {
        //task.getRelatedSkill().remove(skill);
        return guideTaskRepository.save(task);
    }*/
}
