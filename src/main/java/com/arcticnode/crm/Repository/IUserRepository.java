package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
}
