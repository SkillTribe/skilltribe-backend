package com.example.SkillTribe.service;

import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.guide.Guide;
import com.example.SkillTribe.model.guide.GuideTask;

import java.util.List;

public interface GuideService {
    List<Guide> getAll();
    Guide getById();
    Guide createGuide(Guide guide);
    Guide updateGuide(Long id, Guide guide);
    void deleteGuide(Guide guide);

    Guide addRelatedSkill(Skill skill);
    Guide removeRelatedSkill(Skill skill);
    Guide addTask(GuideTask guideTask);
    Guide removeTask(GuideTask guideTask);
}
