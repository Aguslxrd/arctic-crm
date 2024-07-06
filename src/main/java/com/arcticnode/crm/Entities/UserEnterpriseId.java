package com.arcticnode.crm.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEnterpriseId implements Serializable {

    private Integer userId;
    private Integer enterpriseId;

}
