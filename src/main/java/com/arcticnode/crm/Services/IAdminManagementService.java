package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.UserType;

public interface IAdminManagementService {

    public void changeUserRole (String email, UserType userType);
}
