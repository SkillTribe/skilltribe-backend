package com.example.SkillTribe.service.repository.guide;

import com.example.SkillTribe.model.guide.GuideTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuideTaskRepository extends JpaRepository<GuideTask, Long> {
    List<GuideTask> findAllByGuidePlanGuideId(Long id);
}
