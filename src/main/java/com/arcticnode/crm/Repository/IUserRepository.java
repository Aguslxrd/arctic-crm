package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    public Optional<UserEntity> findByEmail(String email);
    public Optional<UserEntity> findByPhone(String phone);
    Page<UserEntity> findAllBySoftDeleteFalse(Pageable pageable);
    public Optional<UserEntity> findByIdentifier(String identifier);

}
