package com.example.SkillTribe.model.guide;

import com.example.SkillTribe.model.Task;
import jakarta.persistence.*;

@Entity
public class GuidePlanItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private GuidePlan guidePlan;
    @ManyToOne
    private Task task;
    private Integer repetition;
}
