package com.example.SkillTribe.dto.request;

import com.example.SkillTribe.model.enums.ETaskGoal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskRequest {
    protected String name;
    protected String description;
    //protected HashSet<Long> relatedSkills;
    protected ETaskGoal taskGoal;
}
