package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntity {

    @Column(unique = true)
    private String email;
    private String passwd;
    @Enumerated(EnumType.STRING)
    private UserType userrole;

}
