package com.arcticnode.crm.Controller;

import com.arcticnode.crm.Dto.CaseDTO;
import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.CaseStatus;
import com.arcticnode.crm.LogUtils.LoggingUtils;
import com.arcticnode.crm.Services.ICaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cases")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:4200")
public class CaseController {

    @Autowired
    private ICaseService caseService;
    @Autowired
    private LoggingUtils loggingUtils;

    @PostMapping
    public ResponseEntity<CaseEntity> createCase(@RequestBody CaseEntity caseEntity) {
        CaseEntity savedCase = caseService.saveCase(caseEntity);
        loggingUtils.logAction("Alta de caso", "Se creo el caso con ID: " + caseEntity.getCaseId());
        return new ResponseEntity<>(savedCase, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CaseEntity>> getAllCases() {
        List<CaseEntity> cases = caseService.findAll();
        return new ResponseEntity<>(cases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaseEntity> getCaseById(@PathVariable Integer id) {
        return caseService.findById(id)
                .map(caseEntity -> new ResponseEntity<>(caseEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaseEntity> updateCase(@PathVariable Integer id, @RequestBody CaseEntity caseEntity) {
        return caseService.findById(id)
                .map(existingCase -> {

                    if (caseEntity.getTitle() != null) {
                        existingCase.setTitle(caseEntity.getTitle());
                    }
                    if (caseEntity.getDescription_case() != null) {
                        existingCase.setDescription_case(caseEntity.getDescription_case());
                    }
                    if (caseEntity.getCase_status() != null) {
                        existingCase.setCase_status(caseEntity.getCase_status());
                    }

                    CaseEntity updatedCase = caseService.saveCase(existingCase);
                    loggingUtils.logAction("Modificacion de caso", "Se modifico el caso con ID: " + caseEntity.getCaseId());
                    return new ResponseEntity<>(updatedCase, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/open-and-in-progress")
    public ResponseEntity<Page<CaseEntity>> getOpenAndInProgressCases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<CaseStatus> statuses = Arrays.asList(CaseStatus.ABIERTO, CaseStatus.EN_PROGRESO);
        Page<CaseEntity> cases = caseService.findByCaseStatusIn(statuses, PageRequest.of(page, size));
        return new ResponseEntity<>(cases, HttpStatus.OK);
    }


    @GetMapping("/open-and-in-progress/{caseId}")
    public ResponseEntity<CaseEntity> getOpenAndInProgressCases(@PathVariable Integer caseId) {
        return caseService.findOpenOrInProgressCaseById(caseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CaseDTO>> getCaseByUserId(@PathVariable Integer userId) {
        List<CaseDTO> userCases = caseService.findByUserId(userId);
        if (userCases.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userCases);

    }

    @GetMapping("/all")
    public ResponseEntity<Long> countAllCases() {
        return ResponseEntity.ok(caseService.countAllCases());
    }

    @GetMapping("/all/opened")
    public ResponseEntity<Long> countOpenedCases() {
        return ResponseEntity.ok(caseService.countAllOpenedCases());
    }

    @GetMapping("/all/inprogress")
    public ResponseEntity<Long> countInProgressCases() {
        return ResponseEntity.ok(caseService.countAllInProgressCases());
    }

}