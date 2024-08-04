package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.CaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICaseService {
    public CaseEntity saveCase(CaseEntity caseEntity);
    public List<CaseEntity> findAll();
    public Optional<CaseEntity> findById(Integer caseId);

    public List<CaseEntity> findByUserId(Integer userId);

    Page<CaseEntity> findByCaseStatusIn(List<CaseStatus> statuses, Pageable pageable);
    Optional<CaseEntity> findOpenOrInProgressCaseById(Integer caseId);
    public Page<CaseEntity> findAllClosedCases(Pageable pageable);

    public long countAllCases();

    public long countAllOpenedCases();
    public long countAllInProgressCases();
}
