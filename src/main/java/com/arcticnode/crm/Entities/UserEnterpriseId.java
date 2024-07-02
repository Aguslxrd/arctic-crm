package com.arcticnode.crm.Entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class UserEnterpriseId implements Serializable {

    private Integer userId;
    private Integer enterpriseId;

}
