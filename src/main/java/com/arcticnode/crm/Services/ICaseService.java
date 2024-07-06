package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.CaseEntity;

import java.util.List;
import java.util.Optional;

public interface ICaseService {
    public CaseEntity saveCase(CaseEntity caseEntity);
    public List<CaseEntity> findAll();
    public Optional<CaseEntity> findById();
}
