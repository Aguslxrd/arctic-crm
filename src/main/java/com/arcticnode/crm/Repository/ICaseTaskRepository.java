package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.CaseTasksEntity;
import com.arcticnode.crm.Entities.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ICaseTaskRepository extends JpaRepository<CaseTasksEntity, Integer> {

    @Transactional
    @Modifying
    @Query("INSERT INTO CaseTasksEntity (caseId, authId, task_content, task_date, task_status) " +
            "VALUES (:caseId, :authId, :task_content, :task_date, :task_status)")
    void saveTask(@Param("caseId") Integer caseId,
                  @Param("authId") Integer authId,
                  @Param("task_content") String task_content,
                  @Param("task_date") LocalDateTime task_date,
                  @Param("task_status") TaskStatus task_status);

    @Query("SELECT t FROM CaseTasksEntity t WHERE t.task_status IN :taskstatus")
    Page<CaseTasksEntity> findByCaseTasksStatusIn(@Param("taskstatus") List<TaskStatus> taskstatus, Pageable pageable);

    List<CaseTasksEntity> findByAuthId(Integer authId);

    List<CaseTasksEntity> findByCaseId(Integer caseId);
}
