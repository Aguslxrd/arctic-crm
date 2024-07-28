package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
