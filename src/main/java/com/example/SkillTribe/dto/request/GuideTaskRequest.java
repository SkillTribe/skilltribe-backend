package com.example.SkillTribe.dto.request;

import com.example.SkillTribe.model.guide.GuideTask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuideTaskRequest  extends TaskRequest {
    private Integer repetition;

    public GuideTask toModel(){
        GuideTask guideTask = new GuideTask();
        guideTask.setName(this.name);
        guideTask.setDescription(this.description);
        //guideTask.setRelatedSkill(this.relatedSkills.stream().map(Skill::new).collect(Collectors.toSet()));
        guideTask.setTaskGoal(this.taskGoal);
        guideTask.setRepetition(this.repetition);
        return guideTask;
    }
}
