package com.example.SkillTribe.model.guide;

import com.example.SkillTribe.model.BaseModel;
import com.example.SkillTribe.model.Skill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "guide")
public class Guide extends BaseModel {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "guide")
    private GuidePlan guidePlan;
    @ManyToMany
    @JoinTable(name = "guide_skill",
            joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "guide_id",
                    referencedColumnName = "id"))
    private Set<Skill> skills;

    public void update(Guide guide) {
        this.name = guide.getName();
        this.description = guide.getDescription();
    }
}
