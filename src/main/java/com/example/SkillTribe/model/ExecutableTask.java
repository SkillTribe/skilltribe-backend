package com.example.SkillTribe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "executable_task")
public class ExecutableTask extends Task {
    @Column(name = "due")
    private LocalDateTime due;
    @Column(name = "comment")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "is_complete")
    private Boolean isComplete;
}
