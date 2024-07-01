package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Entities.EnterpriseEntity;
import com.arcticnode.crm.Repository.IEnterpriseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
