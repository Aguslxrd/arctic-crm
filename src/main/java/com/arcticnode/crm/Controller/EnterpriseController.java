package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Entities.EnterpriseEntity;
import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.LogUtils.LoggingUtils;
import com.arcticnode.crm.Repository.IEnterpriseRepository;
import com.arcticnode.crm.Services.IEnterpriseService;
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
    private IEnterpriseService iEnterpriseService;
    @Autowired
    private LoggingUtils loggingUtils;

    @GetMapping
    public ResponseEntity<Iterable<EnterpriseEntity>> getAllEnterprises(){
        return ResponseEntity.ok(iEnterpriseService.findAllBySoftDeleteFalse());
    }

    @PostMapping
    public ResponseEntity<EnterpriseEntity> saveEnterprise(@RequestBody EnterpriseEntity enterprise) {
        if (enterprise == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        iEnterpriseService.save(enterprise);
        loggingUtils.logAction("Alta de empresa", "Se creo la empresa " + enterprise.getName_enterprise() + " con ID: " + enterprise.getEnterpriseid());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{enterpriseId}")
    public ResponseEntity<EnterpriseEntity> softDeleteEnterprise(@PathVariable Integer enterpriseId){

        if (iEnterpriseService.findById(enterpriseId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        iEnterpriseService.softDeleteById(enterpriseId);
        loggingUtils.logAction("Baja de empresa", "Se elimino la empresa con ID: " + enterpriseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<EnterpriseEntity>> getEnterpriseByEmail(@PathVariable String email) {
        try {
            Optional<EnterpriseEntity> user = iEnterpriseService.findByEmail(email);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Optional<EnterpriseEntity>> getEnterpriseByPhone(@PathVariable String phone) {
        try {
            Optional<EnterpriseEntity> user = iEnterpriseService.findByPhone(phone);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Optional<EnterpriseEntity>> getEnterpriseByRut(@PathVariable String rut) {
        try {
            Optional<EnterpriseEntity> user = iEnterpriseService.findByRut(rut);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name/{enterprisename}")
    public ResponseEntity<Optional<EnterpriseEntity>> getEnterpriseByName(@PathVariable String enterprisename) {
        try {
            Optional<EnterpriseEntity> user = iEnterpriseService.findByName_enterprise(enterprisename);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{enterpriseId}")
    public ResponseEntity<EnterpriseEntity> getEnterpriseById(@PathVariable Integer enterpriseId) {
        return iEnterpriseService.findById(enterpriseId)
                .filter(enterprise -> !enterprise.getSoftDelete())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
