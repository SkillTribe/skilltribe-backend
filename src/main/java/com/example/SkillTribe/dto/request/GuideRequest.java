package com.example.SkillTribe.dto.request;

import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.guide.Guide;
import com.example.SkillTribe.model.guide.GuidePlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GuideRequest {
    private String name;
    private String description;
    private GuidePlanRequest guidePlan;
    private Set<Long> skills;

    public Guide toModel(){
        Guide guide = new Guide();
        guide.setName(this.name);
        guide.setDescription(this.description);
        guide.setGuidePlan(guidePlan.toModel());
        guide.setSkills(skills.stream().map(Skill::new).collect(Collectors.toSet()));
        return guide;
    }
}
