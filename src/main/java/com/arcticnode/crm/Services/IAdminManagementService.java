package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Entities.UserType;

import java.util.List;
import java.util.Optional;

public interface IAdminManagementService {

    public void changeUserRole (String email, UserType userType);

    public Optional<AuthEntity> findById(Integer userId);

    public void deleteById(Integer userId);

    public List<AuthEntity> getAllUsers();

    public AuthEntity editUser(AuthEntity userToEdit);
}
