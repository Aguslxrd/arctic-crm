package com.arcticnode.crm.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEnterpriseId implements Serializable {
    @Column(name = "userid")
    private Integer userId;

    @Column(name = "enterpriseid")
    private Integer enterpriseId;
}
