package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.InteractionsEntity;
import com.arcticnode.crm.Repository.IInteractionRepository;
import com.arcticnode.crm.Services.IInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InteractionServiceImpl implements IInteractionService {

    @Autowired
    private IInteractionRepository iInteractionRepository;
    @Override
    public InteractionsEntity saveInteraction(InteractionsEntity interactionsEntity) {
        if (interactionsEntity.getInteraction_date() == null) {
            interactionsEntity.setInteraction_date(LocalDateTime.now());
        }
        return iInteractionRepository.save(interactionsEntity);
    }

    @Override
    public List<InteractionsEntity> findAll() {
        return iInteractionRepository.findAll();
    }

    @Override
    public List<InteractionsEntity> findByCaseId(Integer caseId) {
        return iInteractionRepository.findByCaseId(caseId);
    }

}
