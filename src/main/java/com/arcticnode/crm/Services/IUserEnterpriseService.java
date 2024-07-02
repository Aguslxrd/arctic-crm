package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserEnterpriseService {
    public List<UserEnterpriseEntity> getAllUserEnterprises();

    public UserEnterpriseEntity saveUserEnterprise(UserEnterpriseEntity userEnterprise);

    public void deleteUserEnterprise(Integer userId, Integer enterpriseId);

    public List<UserEnterpriseEntity> findByUserId(Integer userId);

    public List<UserEnterpriseEntity> findByEnterpriseId(Integer enterpriseId);

    public Optional<UserEnterpriseEntity> findByUserIdAndEnterpriseId(Integer userId, Integer enterpriseId);

}