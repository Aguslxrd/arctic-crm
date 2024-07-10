package com.arcticnode.crm.Services;

import com.arcticnode.crm.Dto.AuthRequest;
import com.arcticnode.crm.Dto.AuthResponse;
import com.arcticnode.crm.Dto.RegisterRequest;
import com.arcticnode.crm.Entities.UserType;

public interface IAuthService {
    public AuthResponse register (RegisterRequest request);
    public AuthResponse authenticate (AuthRequest request);

    public void changeUserRole (String email, UserType userType);
}
