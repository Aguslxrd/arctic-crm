package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Dto.UserRoleToChange;
import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Repository.Admin.IAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
public class AdminManagementController {

    @Autowired
    private IAuthRepository iAuthRepository;

    @PutMapping("/users/changerole")
    public ResponseEntity<AuthEntity> changeUserRole(@RequestBody UserRoleToChange userRoleToChange){

        iAuthRepository.updateUserRole(userRoleToChange.getEmail(), userRoleToChange.getUserType());
        return ResponseEntity.ok().build();
    }

}
