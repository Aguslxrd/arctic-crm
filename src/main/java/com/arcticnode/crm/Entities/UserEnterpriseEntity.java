package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_enterprise")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEnterpriseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @MapsId("enterpriseId")
    @JoinColumn(name = "enterpriseId")
    private EnterpriseEntity enterprise;
}
