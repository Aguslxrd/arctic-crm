package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "interactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InteractionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer interactionId;

    @Column(nullable = false)
    private Integer caseId;

    @Column(nullable = false)
    private Integer userId;

    @Column(columnDefinition = "TEXT")
    private String interaction_text;

    @Column(name = "interaction_date")
    @CreationTimestamp
    private LocalDateTime interaction_date;
}
