package com.arcticnode.crm.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(unique = true)
    private String email;
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
