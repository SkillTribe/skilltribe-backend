package com.example.SkillTribe.dto.response;

import com.example.SkillTribe.model.ExecutableTask;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExecutableTaskResponse extends TaskResponse {
    private LocalDateTime due;
    private String comment;
    private Boolean isComplete;

    public ExecutableTaskResponse(ExecutableTask executableTask){
        super(executableTask);
        this.due = executableTask.getDue();
        this.comment = executableTask.getComment();
        this.isComplete = executableTask.getIsComplete();
    }
}
