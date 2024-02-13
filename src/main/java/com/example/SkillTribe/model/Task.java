package com.example.SkillTribe.model;

import com.example.SkillTribe.model.enums.ETaskGoal;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Task {
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
    protected Set<Skill> relatedSkill;
    @Column(name = "experience")
    protected Integer experience;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_goal")
    protected ETaskGoal taskGoal;

    public void update(Task task) {
        this.description = task.description;
        this.experience = task.experience;
        this.taskGoal = task.taskGoal;
    }
}
