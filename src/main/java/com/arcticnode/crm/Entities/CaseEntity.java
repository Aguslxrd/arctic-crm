package com.arcticnode.crm.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cases")
public class CaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer caseId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description_case;

    @Column(name = "date_created")
    private LocalDateTime date_created;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_status")
    private CaseStatus case_status;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity user;

}