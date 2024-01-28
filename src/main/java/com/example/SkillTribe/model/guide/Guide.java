package com.example.SkillTribe.model.guide;

import com.example.SkillTribe.model.BaseModel;
import com.example.SkillTribe.model.Skill;
import com.example.SkillTribe.model.User;
import jakarta.persistence.*;

import java.util.HashSet;

@Entity
public class Guide extends BaseModel {
    private String name;
    private String description;
    @ManyToOne
    private Skill skills;
    @OneToMany
    private HashSet<GuideTask> guideTasks;
    @OneToOne
    private GuidePlan guidePlan;
    @ManyToMany
    private HashSet<User> users;

}
