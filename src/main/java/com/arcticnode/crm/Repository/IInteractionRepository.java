package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.InteractionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IInteractionRepository extends JpaRepository<InteractionsEntity, Integer> {
    @Transactional
    @Modifying
    @Query("INSERT INTO InteractionsEntity (caseId, authId, interaction_text) " +
            "VALUES (:caseId, :authId, :interactionText)")
    public void saveInteraction(@Param("caseId") Integer caseId,
                         @Param("authId") Integer authId,
                         @Param("interactionText") String interactionText);
}
