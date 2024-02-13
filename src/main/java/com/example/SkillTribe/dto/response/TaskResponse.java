package com.example.SkillTribe.dto.response;

import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.Task;
import com.example.SkillTribe.model.enums.ETaskGoal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponse {
    protected Long id;
    protected String name;
    protected String description;
    protected Set<SkillResponse> relatedSkill;
    protected Integer experience;
    protected ETaskGoal taskGoal;

    public TaskResponse(Task task){
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.relatedSkill = task.getRelatedSkill().stream().map(SkillResponse::new).collect(Collectors.toSet());
        this.experience = task.getExperience();
        this.taskGoal = task.getTaskGoal();
    }
}
