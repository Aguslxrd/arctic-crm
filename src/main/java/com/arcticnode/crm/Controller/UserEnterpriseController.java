package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import com.arcticnode.crm.Services.IUserEnterpriseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user-enterprises")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class UserEnterpriseController {

    private final IUserEnterpriseService userEnterpriseService;

    @PostMapping
    public ResponseEntity<UserEnterpriseEntity> saveUserEnterprise(@RequestBody UserEnterpriseEntity userEnterprise) {
        UserEnterpriseEntity savedUserEnterprise = userEnterpriseService.saveUserEnterprise(userEnterprise);
        return new ResponseEntity<>(savedUserEnterprise, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/{enterpriseId}")
    public ResponseEntity<Void> deleteUserEnterprise(@PathVariable Integer userId, @PathVariable Integer enterpriseId) {
        userEnterpriseService.deleteUserEnterprise(userId, enterpriseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserEnterpriseEntity>> getUserEnterprisesByUserId(@PathVariable Integer userId) {
        List<UserEnterpriseEntity> userEnterprises = userEnterpriseService.findByUserId(userId);
        return ResponseEntity.ok(userEnterprises);
    }

    @GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<List<UserEnterpriseEntity>> getUserEnterprisesByEnterpriseId(@PathVariable Integer enterpriseId) {
        List<UserEnterpriseEntity> userEnterprises = userEnterpriseService.findByEnterpriseId(enterpriseId);
        log.info(userEnterprises.toString());
        return ResponseEntity.ok(userEnterprises);
    }

    @GetMapping
    public ResponseEntity<List<UserEnterpriseEntity>> getAllUserEnterprises() {
        List<UserEnterpriseEntity> userEnterprises = userEnterpriseService.getAllUserEnterprises();
        return ResponseEntity.ok(userEnterprises);
    }

    @GetMapping("/{userId}/{enterpriseId}")
    public ResponseEntity<UserEnterpriseEntity> getUserEnterpriseByIds(@PathVariable Integer userId, @PathVariable Integer enterpriseId) {
        Optional<UserEnterpriseEntity> userEnterprise = userEnterpriseService.findByUserIdAndEnterpriseId(userId, enterpriseId);
        return userEnterprise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

