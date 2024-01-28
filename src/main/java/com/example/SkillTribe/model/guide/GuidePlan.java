package com.example.SkillTribe.model.guide;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
public class GuidePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private HashSet<GuidePlanItem> guidePlanItems;
    @OneToOne
    private Guide guide;
}
