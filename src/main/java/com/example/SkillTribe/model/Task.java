package com.example.SkillTribe.model;

import com.example.SkillTribe.model.enums.ETaskGoal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    protected Long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "description")
    protected String description;
    @ManyToMany
    @JoinTable(name = "task_skill",
            joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id",
                    referencedColumnName = "id"))
    protected HashSet<Skill> relatedSkill;
    @Column(name = "experience")
    protected Integer experience;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_goal")
    protected ETaskGoal taskGoal;
}
