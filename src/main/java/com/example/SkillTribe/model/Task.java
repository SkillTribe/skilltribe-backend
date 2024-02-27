package com.example.SkillTribe.model;

import com.example.SkillTribe.model.enums.ETaskGoal;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
public abstract class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @Column(name = "name", unique = true)
    protected String name;
    @Column(name = "description")
    protected String description;
    /*@ManyToMany
    @JoinTable(name = "task_skill",
            joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id",
                    referencedColumnName = "id"))
    protected Set<Skill> relatedSkill;*/
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_goal")
    protected ETaskGoal taskGoal;

    public void update(Task task) {
        this.name = task.name;
        this.description = task.description;
        this.taskGoal = task.taskGoal;
    }
}
