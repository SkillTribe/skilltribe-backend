package com.example.SkillTribe.model;

import com.example.SkillTribe.model.enums.ESkillLevel;
import com.example.SkillTribe.model.enums.ETaskGoal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Task extends BaseModel {
    private String name;
    private String description;
    @ManyToOne
    private Skill relatedSkill;
    @Enumerated(value = EnumType.STRING)
    private ESkillLevel relatedSkillLevel;
    private Integer experience;
    private LocalDateTime completion;
    @Enumerated(value = EnumType.STRING)
    private ETaskGoal taskGoal;
}
