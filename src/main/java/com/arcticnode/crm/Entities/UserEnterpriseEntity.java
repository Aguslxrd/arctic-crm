package com.arcticnode.crm.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JoinColumn(name = "userid")
    @JsonBackReference(value = "user-userEnterprise")
    private UserEntity user;

    @ManyToOne
    @MapsId("enterpriseId")
    @JoinColumn(name = "enterpriseid")
    @JsonBackReference(value = "enterprise-userEnterprise")
    private EnterpriseEntity enterprise;

    public UserEnterpriseEntity(Integer userId, Integer enterpriseId) {
        this.id = new UserEnterpriseId(userId, enterpriseId);
    }

}
