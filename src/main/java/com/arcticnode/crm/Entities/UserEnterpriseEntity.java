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

    @EmbeddedId
    private UserEnterpriseId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private UserEntity user;

    @ManyToOne
    @MapsId("enterpriseId")
    @JoinColumn(name = "enterpriseId", referencedColumnName = "enterpriseId")
    private EnterpriseEntity enterprise;
}
