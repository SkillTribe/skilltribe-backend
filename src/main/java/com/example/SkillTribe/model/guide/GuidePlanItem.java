package com.example.SkillTribe.model.guide;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Entity
@Getter
@Setter
@Table(name = "guide_plan_item")
public class GuidePlanItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "guide_plan_id")
    private GuidePlan guidePlan;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "guidePlanItem")
    private GuideTask guideTask;
}
