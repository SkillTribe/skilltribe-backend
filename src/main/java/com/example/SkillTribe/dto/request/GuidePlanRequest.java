package com.example.SkillTribe.dto.request;

import com.example.SkillTribe.model.guide.GuidePlan;
import com.example.SkillTribe.model.guide.GuideTask;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GuidePlanRequest {
    private Set<Long> guideTasks;

    public GuidePlan toModel(){
        GuidePlan guidePlan = new GuidePlan();
        guidePlan.setGuideTasks(guideTasks.stream().map(GuideTask::new).collect(Collectors.toSet()));
        return guidePlan;
    }
}
