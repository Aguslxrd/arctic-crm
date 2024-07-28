package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Dto.AuthRequest;
import com.arcticnode.crm.Dto.AuthResponse;
import com.arcticnode.crm.Dto.RegisterRequest;
import com.arcticnode.crm.LogUtils.LoggingUtils;
import com.arcticnode.crm.Services.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
public class AuthController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private LoggingUtils loggingUtils;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {

        log.info("login request {}", request);
        loggingUtils.logAction("Inicio de sesion", "usuario : " + request.getEmail());
        return ResponseEntity.ok(authService.authenticate(request));
    }


}