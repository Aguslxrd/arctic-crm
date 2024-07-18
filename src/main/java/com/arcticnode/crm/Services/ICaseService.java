package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.CaseStatus;

import java.util.List;
import java.util.Optional;

public interface ICaseService {
    public CaseEntity saveCase(CaseEntity caseEntity);
    public List<CaseEntity> findAll();
    public Optional<CaseEntity> findById(Integer caseId);

    public List<CaseEntity> findByUserId(Integer userId);

    public List<CaseEntity> findByCaseStatusIn(List<CaseStatus> statuses);
}
