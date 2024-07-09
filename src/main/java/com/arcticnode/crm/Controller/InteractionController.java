package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.InteractionsEntity;
import com.arcticnode.crm.Services.IInteractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interactions")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
public class InteractionController {

    @Autowired
    private IInteractionService iInteractionService;

    @PostMapping
    public ResponseEntity<InteractionsEntity> createInteraction(@RequestBody InteractionsEntity interactionsEntity) {
        InteractionsEntity savedInteraction = iInteractionService.saveInteraction(interactionsEntity);
        log.info("interaction object data {}" + savedInteraction);
        return new ResponseEntity<>(savedInteraction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InteractionsEntity>> getAllCases() {
        List<InteractionsEntity> interactions = iInteractionService.findAll();
        return new ResponseEntity<>(interactions, HttpStatus.OK);
    }
}
