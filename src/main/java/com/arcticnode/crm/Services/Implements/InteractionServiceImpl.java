package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Dto.InteractionDto;
import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Entities.InteractionsEntity;
import com.arcticnode.crm.Repository.Admin.IAuthRepository;
import com.arcticnode.crm.Repository.IInteractionRepository;
import com.arcticnode.crm.Repository.IUserRepository;
import com.arcticnode.crm.Services.IInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InteractionServiceImpl implements IInteractionService {

    @Autowired
    private IInteractionRepository iInteractionRepository;

    @Autowired
    private IAuthRepository iAuthRepository;

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
    public Optional<InteractionsEntity> findById(Integer interactionId) {
        return iInteractionRepository.findById(interactionId);
    }

    @Override
    public List<InteractionDto> findByCaseId(Integer caseId) {
        List<InteractionsEntity> entities = iInteractionRepository.findByCaseId(caseId);
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private InteractionDto convertToDto(InteractionsEntity entity) {
        String adminName = getAdminNameById(entity.getAuthId());
        return InteractionDto.builder()
                .interactionId(entity.getInteractionId())
                .caseId(entity.getCaseId())
                .authId(entity.getAuthId())
                .adminName(adminName) // Usa el nombre obtenido
                .interaction_text(entity.getInteraction_text())
                .interaction_date(entity.getInteraction_date())
                .build();
    }

    public String getAdminNameById(Integer authId) {
        return iAuthRepository.findById(authId)
                .map(AuthEntity::getAdminName)
                .orElse("Unknown");
    }

    @Override
    public List<InteractionsEntity> findByAuthId(Integer authId){
        return iInteractionRepository.findByAuthId(authId);
    }
}
