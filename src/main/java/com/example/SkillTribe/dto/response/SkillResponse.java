package com.example.SkillTribe.dto.response;

import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.enums.ESkillLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SkillResponse {
    private Long id;
    private String name;
    private String description;
    private Set<SkillResponse> prerequisiteSkills;
    private ESkillLevel skillLevel;

    public SkillResponse(Skill skill){
        this.id = skill.getId();
        this.name = skill.getName();
        this.description = skill.getDescription();
        this.prerequisiteSkills = skill.getPrerequisiteSkills().stream().map(SkillResponse::new).collect(Collectors.toSet());
        this.skillLevel = skill.getSkillLevel();
    }
}
