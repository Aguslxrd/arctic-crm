package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserEnterpriseService {
    public UserEnterpriseEntity save(UserEnterpriseEntity userEnterpriseEntity);
    @Transactional(readOnly = true)
    public List<UserEnterpriseEntity> findByIdUserId(Integer id);

    public List<UserEnterpriseEntity> findByIdEnterpriseId(Integer enterpriseId);

    public void deleteByIdUserIdAndIdEnterpriseId(Integer userId, Integer enterpriseId);
    public boolean existsByIdUserIdAndIdEnterpriseId(Integer userId, Integer enterpriseId);
}
