package com.example.SkillTribe.model.guide;

import com.example.SkillTribe.model.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "guide_task")
@NoArgsConstructor
public class GuideTask extends Task {
    @OneToOne
    @JoinColumn(name = "guide_plan_id")
    private GuidePlan guidePlan;
    @Column(name = "repetition")
    private Integer repetition;
}
