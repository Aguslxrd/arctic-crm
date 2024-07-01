package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserEnterpriseRepository extends JpaRepository<UserEnterpriseEntity, Integer> {
    public void assignUserToEnterprise(UserEnterpriseEntity userEnterpriseEntity);

    public List<UserEnterpriseEntity> findByIdUserId(Integer userId);

    public List<UserEnterpriseEntity> findByIdEnterpriseId(Integer enterpriseId);

    public void deleteByIdUserIdAndIdEnterpriseId(Integer userId, Integer enterpriseId);
    public boolean existsByIdUserIdAndIdEnterpriseId(Integer userId, Integer enterpriseId);
}