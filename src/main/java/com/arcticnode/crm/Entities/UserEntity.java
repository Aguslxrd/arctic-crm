package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
