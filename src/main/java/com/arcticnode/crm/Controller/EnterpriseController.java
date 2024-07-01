package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Entities.EnterpriseEntity;
import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Repository.IEnterpriseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/enterprises")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
public class EnterpriseController {

    @Autowired
    private IEnterpriseRepository iEnterpriseRepository;

    @GetMapping
    public ResponseEntity<Iterable<EnterpriseEntity>> getAllEnterprises(){
        return ResponseEntity.ok(iEnterpriseRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<EnterpriseEntity> saveEnterprise(@RequestBody EnterpriseEntity enterprise) {
        if (enterprise == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        iEnterpriseRepository.save(enterprise);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{enterpriseId}")
    public ResponseEntity<EnterpriseEntity> deleteEnterprise(@PathVariable Integer enterpriseId){

        if (iEnterpriseRepository.findById(enterpriseId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        iEnterpriseRepository.deleteById(enterpriseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<EnterpriseEntity>> getEnterpriseByEmail(@PathVariable String email) {
        try {
            Optional<EnterpriseEntity> user = iEnterpriseRepository.findByEmail(email);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Optional<EnterpriseEntity>> getEnterpriseByPhone(@PathVariable String phone) {
        try {
            Optional<EnterpriseEntity> user = iEnterpriseRepository.findByPhone(phone);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Optional<EnterpriseEntity>> getEnterpriseByRut(@PathVariable String rut) {
        try {
            Optional<EnterpriseEntity> user = iEnterpriseRepository.findByRut(rut);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
