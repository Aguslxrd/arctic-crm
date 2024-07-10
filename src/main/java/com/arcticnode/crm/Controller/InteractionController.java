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
    public ResponseEntity<List<InteractionsEntity>> getAllInteractions() {
        List<InteractionsEntity> interactions = iInteractionService.findAll();
        return new ResponseEntity<>(interactions, HttpStatus.OK);
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<InteractionsEntity>> getAllInteractionsByCaseId(@PathVariable Integer caseId){
        List<InteractionsEntity> interactionsByCaseId = iInteractionService.findByCaseId(caseId);
        if (interactionsByCaseId.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(interactionsByCaseId, HttpStatus.OK);

    }

    @GetMapping("/user/{authId}")
    public ResponseEntity<List<InteractionsEntity>> getAllInteractionsByAuthId(@PathVariable Integer authId){
        List<InteractionsEntity> interactionsByAuthId = iInteractionService.findByAuthId(authId);
        if (interactionsByAuthId.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(interactionsByAuthId, HttpStatus.OK);

    }

}
