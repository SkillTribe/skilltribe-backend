package com.example.SkillTribe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill extends BaseModel{
    private String name;
    private String description;
    @ElementCollection(targetClass = Skill.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "id"))
    private HashSet<Skill> prerequisiteSkills;

}
