package com.arcticnode.crm.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer userid;
    private String firstname;
    private String secondname;
    private String firstlastname;
    private String secondlastname;
    private String phone;
    @Column(unique = true)
    private String email;
    private String address;
    private String identifier;
    @Column(name = "softDelete")
    private Boolean softDelete = false;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user-userEnterprise")
    private List<UserEnterpriseEntity> userEnterprises;
}
