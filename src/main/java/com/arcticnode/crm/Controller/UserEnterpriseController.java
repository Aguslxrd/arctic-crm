package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Entities.UserEnterpriseEntity;
import com.arcticnode.crm.Entities.UserEnterpriseId;
import com.arcticnode.crm.LogUtils.LoggingUtils;
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
    @Autowired
    private IUserEnterpriseService userEnterpriseService;
    @Autowired
    private LoggingUtils loggingUtils;

    @PostMapping
    public ResponseEntity<Void> assignUserToEnterprise(@RequestBody UserEnterpriseId userEnterpriseId) {
        userEnterpriseService.saveUserEnterprise(userEnterpriseId.getUserId(), userEnterpriseId.getEnterpriseId());
        loggingUtils.logAction("Asignacion de empresa", "Se asigno empresa con ID: " + userEnterpriseId.getEnterpriseId() +
                " al usuario con ID: " + userEnterpriseId.getUserId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/{enterpriseId}")
    public ResponseEntity<Void> deleteUserEnterprise(@PathVariable Integer userId, @PathVariable Integer enterpriseId) {
        userEnterpriseService.deleteUserEnterprise(userId, enterpriseId);
        loggingUtils.logAction("Desvinculacion de empresa", "Se removio usuario con ID: " + userId +
                " de la empresa con ID: " + enterpriseId);
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

