package com.arcticnode.crm.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "enterprise")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enterpriseid;
    private String name_enterprise;
    private String rut;
    private String address;
    private String phone;
    private String email;
    private String web_site;
    @Column(name = "softDelete")
    private Boolean softDelete = false;

    @OneToMany(mappedBy = "enterprise")
    @JsonManagedReference(value = "enterprise-userEnterprise")
    private List<UserEnterpriseEntity> userEnterprises;
}
