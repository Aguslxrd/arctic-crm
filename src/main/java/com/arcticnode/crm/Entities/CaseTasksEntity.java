package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "case_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaseTasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_id;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer authId;

    @Column(nullable = false, length = 120)
    private String task_content;

    private LocalDateTime task_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus task_status;
}
