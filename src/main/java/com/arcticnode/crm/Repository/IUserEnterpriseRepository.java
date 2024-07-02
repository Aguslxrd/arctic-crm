package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import com.arcticnode.crm.Entities.UserEnterpriseId;
import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Entities.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserEnterpriseRepository extends JpaRepository<UserEnterpriseEntity, UserEnterpriseId> {

    @Transactional
    @Modifying
    @Query("DELETE FROM UserEnterpriseEntity ue WHERE ue.id.userId = :userId AND ue.id.enterpriseId = :enterpriseId")
    void deleteByUserIdAndEnterpriseId(@Param("userId") Integer userId, @Param("enterpriseId") Integer enterpriseId);

    @Query("SELECT ue FROM UserEnterpriseEntity ue WHERE ue.id.userId = :userId")
    List<UserEnterpriseEntity> findByUserId(@Param("userId") Integer userId);

    List<UserEnterpriseEntity> findByUser(UserEntity user);

    List<UserEnterpriseEntity> findByEnterprise(EnterpriseEntity enterprise);

    Optional<UserEnterpriseEntity> findByIdUserIdAndIdEnterpriseId(Integer userId, Integer enterpriseId);
}
