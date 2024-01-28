package com.example.SkillTribe.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Task task;
    @ManyToOne
    private User user;
    private LocalDateTime due;
    private boolean isCompleted;
}
