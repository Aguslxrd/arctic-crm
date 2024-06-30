package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Entities.UserEntity;
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
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping
    public ResponseEntity<Iterable<UserEntity>> getAllUsers(){
        return ResponseEntity.ok(iUserService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        iUserService.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Integer userId){

        if (iUserService.findById(userId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        iUserService.deleteById(userId);
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

    //identifier / phone.

}
