package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "logging")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoggingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer log_id;
    @Column(name = "user_id")
    private Integer userId;
    private String user_action;
    private String details;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
