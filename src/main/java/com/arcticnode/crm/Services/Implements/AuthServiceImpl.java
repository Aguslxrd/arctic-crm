package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Dto.AuthRequest;
import com.arcticnode.crm.Dto.AuthResponse;
import com.arcticnode.crm.Dto.RegisterRequest;
import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Entities.UserType;
import com.arcticnode.crm.Jwt.JwtService;
import com.arcticnode.crm.Repository.Admin.IAuthRepository;
import com.arcticnode.crm.Services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IAuthRepository iAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = AuthEntity.builder()
                .email(request.getEmail())
                .passwd(passwordEncoder.encode(request.getPasswd()))
                .adminname(request.getAdminname())
                .userrole(UserType.USER)
                .build();
        iAuthRepository.save(user);

        System.out.println("Usuario registrado: " + user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }


    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPasswd()
                    )
            );
            var user = iAuthRepository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .userId(String.valueOf(user.getId()))
                    .build();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new RuntimeException("Credenciales inválidas ", e);
        }

    }

}