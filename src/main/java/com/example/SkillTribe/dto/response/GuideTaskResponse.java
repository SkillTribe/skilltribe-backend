package com.example.SkillTribe.dto.response;

import com.example.SkillTribe.model.guide.GuideTask;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GuideTaskResponse extends TaskResponse {
    private Integer repetition;

    public GuideTaskResponse(GuideTask guideTask){
        super(guideTask);
        this.repetition = guideTask.getRepetition();
    }
}
