package com.example.SkillTribe.service;

import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.guide.Guide;
import com.example.SkillTribe.model.guide.GuideTask;

import java.util.List;

public interface GuideService {
    List<Guide> getAll();
    Guide getById(Long id);
    Guide createGuide(Guide guide);
    Guide updateGuide(Long id, Guide guide);
    void deleteGuide(Guide guide);

    Guide addRelatedSkill(Long id, Skill skill);
    Guide removeRelatedSkill(Long id, Skill skill);

    Guide addTask(Long guideId, GuideTask guideTask);

    Guide removeTask(Long guideId, Long guideTaskId);

}
