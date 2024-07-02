package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import com.arcticnode.crm.Repository.IUserEnterpriseRepository;
import com.arcticnode.crm.Services.IUserEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEnterpriseServiceImpl implements IUserEnterpriseService {

    private final IUserEnterpriseRepository userEnterpriseRepository;

    @Autowired
    public UserEnterpriseServiceImpl(IUserEnterpriseRepository userEnterpriseRepository) {
        this.userEnterpriseRepository = userEnterpriseRepository;
    }

    @Override
    public List<UserEnterpriseEntity> getAllUserEnterprises() {
        return userEnterpriseRepository.findAll();
    }

    @Override
    public UserEnterpriseEntity saveUserEnterprise(UserEnterpriseEntity userEnterprise) {
        return userEnterpriseRepository.save(userEnterprise);
    }

    @Override
    public void deleteUserEnterprise(Integer userId, Integer enterpriseId) {
        userEnterpriseRepository.deleteByUserIdAndEnterpriseId(userId, enterpriseId);
    }

    @Override
    public List<UserEnterpriseEntity> findByUserId(Integer userId) {
        return userEnterpriseRepository.findByUserId(userId);
    }

    @Override
    public List<UserEnterpriseEntity> findByEnterpriseId(Integer enterpriseId) {
        return null;
    }

    @Override
    public Optional<UserEnterpriseEntity> findByUserIdAndEnterpriseId(Integer userId, Integer enterpriseId) {
        return Optional.empty();
    }

}
