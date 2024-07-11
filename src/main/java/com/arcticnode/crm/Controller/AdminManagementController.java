package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Dto.AuthResponse;
import com.arcticnode.crm.Dto.RegisterRequest;
import com.arcticnode.crm.Dto.UserRoleToChange;
import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Repository.Admin.IAdminManagementRepository;
import com.arcticnode.crm.Repository.Admin.IAuthRepository;
import com.arcticnode.crm.Services.IAdminManagementService;
import com.arcticnode.crm.Services.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
public class AdminManagementController {

    @Autowired
    private IAdminManagementService iAdminManagementService;

    @Autowired
    private IAuthService authService;

    @PutMapping("/users/changerole")
    public ResponseEntity<AuthEntity> changeUserRole(@RequestBody UserRoleToChange userRoleToChange){

        iAdminManagementService.changeUserRole(userRoleToChange.getEmail(), userRoleToChange.getUserType());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/users/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        log.info("register request {}", request);
        return ResponseEntity.ok(authService.register(request));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<AuthEntity> deleteUserById(@PathVariable Integer userId){
        if (userId == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();

    }

    @GetMapping("/users")
    public ResponseEntity<List<AuthEntity>> getAllRegisteredUsers(){
        List<AuthEntity> allUsers = iAdminManagementService.getAllUsers();
        if (allUsers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allUsers);
    }

}
