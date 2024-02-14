package com.example.SkillTribe.dto.response;

import com.example.SkillTribe.model.guide.Guide;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GuideResponse {
    private Long id;
    private String name;
    private String description;
    private GuidePlanResponse guidePlan;
    private Set<SkillResponse> skills;

    public GuideResponse(Guide guide){
        this.id = guide.getId();
        this.name = guide.getName();
        this.description = guide.getDescription();
        this.guidePlan = new GuidePlanResponse(guide.getGuidePlan());
        this.skills = guide.getSkills().stream().map(SkillResponse::new).collect(Collectors.toSet());
    }
}
