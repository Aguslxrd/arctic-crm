package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "auth")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String email;
    @Getter
    @Column(unique = true, name = "username")
    private String adminName;
    private String passwd;
    @Enumerated(EnumType.STRING)
    private UserType userrole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) () -> userrole.name());
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() { //username=email
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
