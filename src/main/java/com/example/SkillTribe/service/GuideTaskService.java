package com.example.SkillTribe.service;

import com.example.SkillTribe.model.guide.GuideTask;

import java.util.List;

public interface GuideTaskService {
    List<GuideTask> getAll();
    List<GuideTask> getAllByGuideId(Long id);
    GuideTask getById(Long id);
    GuideTask createGuideTask(GuideTask task);
    GuideTask updateGuideTask(Long id, GuideTask task);
    void deleteGuideTask(GuideTask task);
   /* GuideTask addRelatedSkill(GuideTask task, Skill skill);
    GuideTask removeRelatedSkill(GuideTask task, Skill skill);*/
}
