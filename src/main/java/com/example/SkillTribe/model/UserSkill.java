package com.example.SkillTribe.model;

import com.example.SkillTribe.model.enums.ESkillLevel;
import jakarta.persistence.*;

@Entity
public class UserSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Skill skill;
    @Enumerated(value = EnumType.STRING)
    private ESkillLevel skillLevel;
    private Integer currentExperience;

}
