package com.arcticnode.crm.Repository.Admin;

import com.arcticnode.crm.Entities.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<AuthEntity, Integer> {

    public Optional<AuthEntity> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE AuthEntity a SET a.userrole = :newRole WHERE a.email = :email")
    public void updateUserRole(String email, String newRole);
}
