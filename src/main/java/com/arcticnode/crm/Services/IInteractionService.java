package com.arcticnode.crm.Services;

import com.arcticnode.crm.Dto.InteractionDto;
import com.arcticnode.crm.Entities.InteractionsEntity;

import java.util.List;

public interface IInteractionService {

    public InteractionsEntity saveInteraction(InteractionsEntity interactionsEntity);
    public List<InteractionsEntity> findAll();
    public List<InteractionDto> findByCaseId(Integer caseId);
    public List<InteractionsEntity> findByAuthId(Integer authId);
}
