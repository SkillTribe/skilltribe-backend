package com.example.SkillTribe.model.guide;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "guide_plan")
public class GuidePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToMany(fetch = FetchType.EAGER)
    private HashSet<GuidePlanItem> guidePlanItems;
    @OneToOne
    @JoinColumn(name = "guide_id")
    private Guide guide;
}
