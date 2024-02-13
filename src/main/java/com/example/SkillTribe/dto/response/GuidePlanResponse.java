package com.example.SkillTribe.dto.response;

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
public class GuidePlanResponse {
    private Long id;
    private Set<GuideTaskResponse> guideTasks;

    public GuidePlanResponse(GuidePlan guidePlan){
        this.id = guidePlan.getId();
        this.guideTasks = guidePlan.getGuideTasks().stream().map(GuideTaskResponse::new).collect(Collectors.toSet());
    }
}
