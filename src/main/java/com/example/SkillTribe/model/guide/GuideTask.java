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
    @ManyToOne
    @JoinColumn(name = "guide_plan_id")
    private GuidePlan guidePlan;
    @Column(name = "repetition")
    private Integer repetition;

    public GuideTask(Long id){
        this.id = id;
    }

    @Override
    public void update(Task task) {
        super.update(task);
        if(task instanceof GuideTask)
            this.repetition = ((GuideTask) task).repetition;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof GuideTask))
            return false;
        return ((GuideTask) obj).getId().equals(this.id);
    }
}
