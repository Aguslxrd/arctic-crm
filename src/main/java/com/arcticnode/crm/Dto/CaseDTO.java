package com.arcticnode.crm.Dto;

import com.arcticnode.crm.Entities.CaseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseDTO {

    private Integer caseId;
    private Integer userId;
    private String adminName;
    private Integer authId;
    private String title;
    private String description_case;
    private LocalDateTime date_created;
    private CaseStatus case_status;

}
