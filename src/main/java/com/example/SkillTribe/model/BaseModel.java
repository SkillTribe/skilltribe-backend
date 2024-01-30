package com.example.SkillTribe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@SQLDelete(sql = "UPDATE table_product SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted <> true")
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @CreatedDate
    @Column(name = "created_at")
    protected LocalDateTime createdAt;
    @Column(name = "created_by")
    protected String createdBy;
    @LastModifiedDate
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;
    @Column(name = "is_deleted")
    protected Boolean isDeleted;
}
