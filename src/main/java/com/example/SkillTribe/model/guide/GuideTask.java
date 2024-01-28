package com.example.SkillTribe.model.guide;

import com.example.SkillTribe.model.Task;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class GuideTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Guide guide;
    @ManyToOne
    private Task task;
    private LocalDateTime due;
    private boolean isCompleted;
}
