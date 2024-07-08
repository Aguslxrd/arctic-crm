package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICaseRepository extends JpaRepository<CaseEntity, Integer> {

    @Transactional
    @Modifying
    @Query("INSERT INTO CaseEntity (userId, title, description_case, case_status) " +
            "VALUES (:userId, :title, :descriptionCase, :caseStatus)")
    void saveCase(@Param("userId") Integer userId,
                  @Param("title") String title,
                  @Param("descriptionCase") String descriptionCase,
                  @Param("caseStatus") CaseStatus caseStatus);

    @Query("SELECT c FROM CaseEntity c WHERE c.case_status IN :statuses")
    List<CaseEntity> findByCaseStatusIn(@Param("statuses") List<CaseStatus> statuses);
}
