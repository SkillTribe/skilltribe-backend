package com.example.SkillTribe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@MappedSuperclass
@SQLDelete(sql = "UPDATE table_product SET deleted = true WHERE id=?")
@SQLRestriction("deleted <> true")
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createdAt;
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Boolean deleted;
}
