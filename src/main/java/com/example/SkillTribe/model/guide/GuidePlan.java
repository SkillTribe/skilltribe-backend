package com.example.SkillTribe.model.guide;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "guide_plan")
public class GuidePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "guidePlan")
    private Set<GuideTask> guideTasks;
    @OneToOne
    @JoinColumn(name = "guide_id")
    private Guide guide;
}
