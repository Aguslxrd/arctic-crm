package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Dto.CaseDTO;
import com.arcticnode.crm.Dto.InteractionDto;
import com.arcticnode.crm.Entities.AuthEntity;
import com.arcticnode.crm.Entities.CaseEntity;
import com.arcticnode.crm.Entities.CaseStatus;
import com.arcticnode.crm.Entities.InteractionsEntity;
import com.arcticnode.crm.Repository.Admin.IAuthRepository;
import com.arcticnode.crm.Repository.ICaseRepository;
import com.arcticnode.crm.Services.ICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CaseServiceImpl implements ICaseService {

    @Autowired
    private ICaseRepository iCaseRepository;
    @Autowired
    private IAuthRepository iAuthRepository;
    @Override
    public CaseEntity saveCase(CaseEntity caseEntity) {
        if (caseEntity.getDate_created() == null) {
            caseEntity.setDate_created(LocalDateTime.now());
        }
        return iCaseRepository.save(caseEntity);
    }

    @Override
    public List<CaseEntity> findAll() {
        return iCaseRepository.findAll();
    }

    @Override
    public Optional<CaseEntity> findById(Integer caseId) {
        return iCaseRepository.findById(caseId);
    }

    @Override
    public List<CaseDTO> findByUserId(Integer userId) {
        return iCaseRepository.findByUserId(userId).stream()
                .filter(caseEntity ->
                        caseEntity.getCase_status() == CaseStatus.ABIERTO ||
                                caseEntity.getCase_status() == CaseStatus.EN_PROGRESO)
                .map(this::convertToDto)  // Convertimos CaseEntity a CaseDTO
                .collect(Collectors.toList());
    }

    public Page<CaseEntity> findByCaseStatusIn(List<CaseStatus> statuses, Pageable pageable) {
        return iCaseRepository.findByCaseStatusIn(statuses, pageable);
    }

    @Override
    public Optional<CaseEntity> findOpenOrInProgressCaseById(Integer caseId) {
        return iCaseRepository.findById(caseId).filter(caseEntity ->
            caseEntity.getCase_status() == CaseStatus.ABIERTO ||
            caseEntity.getCase_status() == CaseStatus.EN_PROGRESO
        );
    }

    @Override
    public Page<CaseEntity> findAllClosedCases(Pageable pageable) {
        List<CaseEntity> allCases = iCaseRepository.findAll();

        //filtros por casos cerrados
        List<CaseEntity> closedCases = allCases.stream()
                .filter(caseEntity -> caseEntity.getCase_status() == CaseStatus.CERRADO)
                .collect(Collectors.toList());

        //indices de la página actual
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), closedCases.size());

        //sublista para la página actual
        List<CaseEntity> pagedCases = closedCases.subList(start, end);

        //pagina de casos cerrados
        return new PageImpl<>(pagedCases, pageable, closedCases.size());
    }

    private CaseDTO convertToDto(CaseEntity caseEntity) {
        return CaseDTO.builder()
                .caseId(caseEntity.getCaseId())
                .userId(caseEntity.getUserId())
                .authId(caseEntity.getAuthId())
                .adminName(getAdminNameById(caseEntity.getAuthId()))
                .title(caseEntity.getTitle())
                .description_case(caseEntity.getDescription_case())
                .date_created(caseEntity.getDate_created())
                .case_status(caseEntity.getCase_status())
                .build();
    }

    public String getAdminNameById(Integer authId) {
        return iAuthRepository.findById(authId)
                .map(AuthEntity::getAdminName)
                .orElse("Unknown");
    }

    @Override
    public long countAllCases() {
        return iCaseRepository.findAll().stream()
                .filter(caseEntity ->
                        caseEntity.getCase_status() == CaseStatus.ABIERTO ||
                        caseEntity.getCase_status() == CaseStatus.EN_PROGRESO)
                .count();
    }

    @Override
    public long countAllOpenedCases() {
        return iCaseRepository.findAll().stream()
                .filter(caseEntity -> caseEntity.getCase_status() == CaseStatus.ABIERTO)
                .count();
    }

    @Override
    public long countAllInProgressCases() {
        return iCaseRepository.findAll().stream()
                .filter(caseEntity -> caseEntity.getCase_status() == CaseStatus.EN_PROGRESO)
                .count();
    }

}
