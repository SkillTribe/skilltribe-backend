package com.example.SkillTribe.model.guide;

import com.example.SkillTribe.model.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "guide_task")
public class GuideTask extends Task {
    @OneToOne
    @JoinColumn(name = "guide_plan_item_id")
    private GuidePlanItem guidePlanItem;
    @Column(name = "repetition")
    private Integer repetition;
}
