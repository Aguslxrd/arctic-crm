package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.CaseTasksEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICaseTaskService {
    public void saveTask(CaseTasksEntity task);
    public Optional<CaseTasksEntity> findById(int taskId);
    public List<CaseTasksEntity> findByAuthId(int authId);
    public List<CaseTasksEntity> findByCaseId(int caseId);
    Page<CaseTasksEntity> findByCaseTasksStatusIn(List<CaseTasksEntity> statuses, Pageable pageable);
}
