package com.arcticnode.crm.Repository.Admin;

import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAdminManagementRepository extends JpaRepository<AuthEntity, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE AuthEntity a SET a.userrole = :userType WHERE a.email = :email")
    public void updateUserRole(String email, UserType userType);

    
}
