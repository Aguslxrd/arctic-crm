package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserEnterpriseRepository extends JpaRepository<UserEnterpriseEntity, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM UserEnterpriseEntity ue WHERE ue.user.userid = :userId AND ue.enterprise.enterpriseid = :enterpriseId")
    void deleteByUserIdAndEnterpriseId(@Param("userId") Integer userId, @Param("enterpriseId") Integer enterpriseId);

    List<UserEnterpriseEntity> findByUserId(Integer userId);

    List<UserEnterpriseEntity> findByEnterpriseId(Integer enterpriseId);

    Optional<UserEnterpriseEntity> findByUserIdAndEnterpriseId(Integer userId, Integer enterpriseId);
}
