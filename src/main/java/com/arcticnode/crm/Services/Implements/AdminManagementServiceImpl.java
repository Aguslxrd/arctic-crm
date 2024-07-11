package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Entities.UserType;
import com.arcticnode.crm.Repository.Admin.IAdminManagementRepository;
import com.arcticnode.crm.Services.IAdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminManagementServiceImpl implements IAdminManagementService {

    @Autowired
    private IAdminManagementRepository iAdminManagementRepository;

    @Override
    public void changeUserRole(String email, UserType userType) {
        iAdminManagementRepository.updateUserRole(email, userType);

    }

    @Override
    public Optional<AuthEntity> findById(Integer userId) {
        return iAdminManagementRepository.findById(userId);
    }

    @Override
    public void deleteById(Integer userId) {
        iAdminManagementRepository.deleteById(userId);
    }


}
