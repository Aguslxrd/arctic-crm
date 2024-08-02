package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Config.AppConfig;
import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.LogUtils.LoggingUtils;
import com.arcticnode.crm.Services.ILoggingService;
import com.arcticnode.crm.Services.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ILoggingService loggingService;
    @Autowired
    private LoggingUtils logUtils;

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> getAllActiveUsers(){
        return ResponseEntity.ok(iUserService.findAllBySoftDeleteFalse());
    }

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        iUserService.save(user);
        logUtils.logAction("Creación de usuario", "Se creó el usuario con ID: " + user.getUserid());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntity> softDeleteUser(@PathVariable Integer userId){

        if (iUserService.findById(userId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        iUserService.softDeleteById(userId);
        logUtils.logAction("Baja de usuario", "Se elimino el usuario con ID: " + userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<UserEntity>> getUserByEmail(@PathVariable String email) {
        try {
            Optional<UserEntity> user = iUserService.findByEmail(email);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Optional<UserEntity>> getUserByPhone(@PathVariable String phone) {
        try {
            Optional<UserEntity> user = iUserService.findByPhone(phone);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/identifier/{identifier}")
    public ResponseEntity<Optional<UserEntity>> getUserByIdentifier(@PathVariable String identifier) {
        try {
            Optional<UserEntity> user = iUserService.findByIdentifier(identifier);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserByIdentifier(@PathVariable Integer userId) {
        return iUserService.findById(userId)
                .filter(user -> !user.getSoftDelete())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Long> countAllUsers() {
        return ResponseEntity.ok(iUserService.countAllUsers());
    }

}
