package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<AuthEntity, Integer> {

    public Optional<AuthEntity> findByEmail(String email);
}
