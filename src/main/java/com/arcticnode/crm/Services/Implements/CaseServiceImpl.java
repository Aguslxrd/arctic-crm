package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.CaseStatus;
import com.arcticnode.crm.Repository.ICaseRepository;
import com.arcticnode.crm.Services.ICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CaseServiceImpl implements ICaseService {

    @Autowired
    private ICaseRepository iCaseRepository;
    @Override
    public CaseEntity saveCase(CaseEntity caseEntity) {
        if (caseEntity.getDate_created() == null) {
            caseEntity.setDate_created(LocalDateTime.now());
        }
        return iCaseRepository.save(caseEntity);
    }

    @Override
    public List<CaseEntity> findAll() {
        return iCaseRepository.findAll();
    }

    @Override
    public Optional<CaseEntity> findById(Integer caseId) {
        return iCaseRepository.findById(caseId);
    }

    @Override
    public List<CaseEntity> findByUserId(Integer userId) {
        return iCaseRepository.findByUserId(userId);
    }

    @Override
    public List<CaseEntity> findByCaseStatusIn(List<CaseStatus> statuses) {
        return iCaseRepository.findByCaseStatusIn(statuses);
    }

    @Override
    public Optional<CaseEntity> findOpenOrInProgressCaseById(Integer caseId) {
        return iCaseRepository.findById(caseId).filter(caseEntity ->
            caseEntity.getCase_status() == CaseStatus.ABIERTO ||
            caseEntity.getCase_status() == CaseStatus.EN_PROGRESO
        );
    }

    @Override
    public List<CaseEntity> findAllClosedCases() {
        return iCaseRepository.findAll().stream().filter(caseEntity ->
                caseEntity.getCase_status() == CaseStatus.CERRADO)
                .collect(Collectors.toList());
    }

}
