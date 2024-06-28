package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Dto.AuthRequest;
import com.arcticnode.crm.Dto.AuthResponse;
import com.arcticnode.crm.Dto.RegisterRequest;
import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Repository.IAuthRepository;
import com.arcticnode.crm.Services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    @Override
    public AuthResponse register(RegisterRequest request) {
        return null;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        return null;
    }
    //to - do

}