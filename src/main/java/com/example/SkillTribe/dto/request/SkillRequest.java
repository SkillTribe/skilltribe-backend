package com.example.SkillTribe.dto.request;

import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.enums.ESkillLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SkillRequest {
    private String name;
    private String description;
    private Set<Long> prerequisiteSkills;
    private ESkillLevel skillLevel;

    public Skill toModel(){
        Skill skill = new Skill();
        skill.setName(this.name);
        skill.setDescription(this.description);
        skill.setPrerequisiteSkills(prerequisiteSkills.stream().map(Skill::new).collect(Collectors.toSet()));
        skill.setSkillLevel(this.skillLevel);
        return skill;
    }
}
