package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(iUserService.findAll());
    }

}
