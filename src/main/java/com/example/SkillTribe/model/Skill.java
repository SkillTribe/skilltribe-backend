package com.example.SkillTribe.model;

import com.example.SkillTribe.model.enums.ESkillLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "skill")
@NoArgsConstructor
public class Skill extends BaseModel{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ElementCollection(targetClass = Skill.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "id"))
    private Set<Skill> prerequisiteSkills;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "skill_level")
    private ESkillLevel skillLevel;

    public Skill(Long id){
        this.id = id;
    }

    public void update(Skill skill) {
        this.name = skill.getName();
        this.description = skill.getDescription();
        this.skillLevel = skill.skillLevel;
    }
}
