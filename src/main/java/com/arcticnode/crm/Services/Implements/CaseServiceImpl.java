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
    public List<CaseEntity> findByCaseStatusIn(List<CaseStatus> statuses) {
        return iCaseRepository.findByCaseStatusIn(statuses);
    }

}
