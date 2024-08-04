package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.EnterpriseEntity;
import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import com.arcticnode.crm.Entities.UserEnterpriseId;
import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Repository.IEnterpriseRepository;
import com.arcticnode.crm.Repository.IUserEnterpriseRepository;
import com.arcticnode.crm.Repository.IUserRepository;
import com.arcticnode.crm.Services.IUserEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEnterpriseServiceImpl implements IUserEnterpriseService {

    private final IUserEnterpriseRepository userEnterpriseRepository;
    private final IUserRepository userRepository;
    private final IEnterpriseRepository enterpriseRepository;

    @Autowired
    public UserEnterpriseServiceImpl(IUserEnterpriseRepository userEnterpriseRepository, IUserRepository userRepository, IEnterpriseRepository enterpriseRepository) {
        this.userEnterpriseRepository = userEnterpriseRepository;
        this.userRepository = userRepository;
        this.enterpriseRepository = enterpriseRepository;
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
        return userEnterpriseRepository.findByEnterpriseId(enterpriseId);
    }

    @Override
    public Optional<UserEnterpriseEntity> findByUserIdAndEnterpriseId(Integer userId, Integer enterpriseId) {
        return userEnterpriseRepository.findById(new UserEnterpriseId(userId, enterpriseId));
    }

    @Override
    public void saveUserEnterprise(Integer userId, Integer enterpriseId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        Optional<EnterpriseEntity> enterpriseOptional = enterpriseRepository.findById(enterpriseId);

        if (userOptional.isPresent() && enterpriseOptional.isPresent()) {
            UserEntity user = userOptional.get();
            EnterpriseEntity enterprise = enterpriseOptional.get();
            UserEnterpriseEntity userEnterprise = new UserEnterpriseEntity();
            userEnterprise.setId(new UserEnterpriseId(userId, enterpriseId));
            userEnterprise.setUser(user);
            userEnterprise.setEnterprise(enterprise);
            userEnterpriseRepository.save(userEnterprise);
        } else {
            throw new IllegalArgumentException("User or Enterprise not found");
        }
    }
}
