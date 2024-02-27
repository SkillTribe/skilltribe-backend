package com.example.SkillTribe.dto.request;

import com.example.SkillTribe.model.ExecutableTask;
import com.example.SkillTribe.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import com.example.SkillTribe.model.Skill;

@Getter
@Setter
@NoArgsConstructor
public class ExecutableTaskRequest extends TaskRequest{
    private LocalDateTime due;
    private String comment;
    private User user;
    private Boolean isComplete;

    public ExecutableTask toModel(){
        ExecutableTask executableTask = new ExecutableTask();
        executableTask.setName(this.name);
        executableTask.setDescription(this.description);
        //executableTask.setRelatedSkill(this.relatedSkills.stream().map(Skill::new).collect(Collectors.toSet()));
        executableTask.setTaskGoal(this.taskGoal);
        executableTask.setDue(this.due);
        executableTask.setComment(this.comment);
        executableTask.setIsComplete(false);
        return executableTask;
    }
}
