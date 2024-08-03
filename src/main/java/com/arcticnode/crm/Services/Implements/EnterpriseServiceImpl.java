package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.CaseStatus;
import com.arcticnode.crm.Entities.EnterpriseEntity;
import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Repository.IEnterpriseRepository;
import com.arcticnode.crm.Services.IEnterpriseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements IEnterpriseService {

    @Autowired
    private IEnterpriseRepository iEnterpriseRepository;
    @Override
    public EnterpriseEntity save(EnterpriseEntity enterprise) {
        return iEnterpriseRepository.save(enterprise);
    }

    @Override
    public Optional<EnterpriseEntity> findById(Integer id) {
        return iEnterpriseRepository.findById(id);
    }

    @Override
    public List<EnterpriseEntity> findAll() {
        return iEnterpriseRepository.findAll();
    }

    @Override
    public List<EnterpriseEntity> findAllBySoftDeleteFalse() {
        return iEnterpriseRepository.findAllBySoftDeleteFalse();
    }

    @Override
    public List<EnterpriseEntity> findAllBySoftDeleteTrue() {
        return iEnterpriseRepository.findAllBySoftDeleteTrue();
    }

    @Override
    public void deleteById(Integer enterpriseId) {
        if (enterpriseId != 0){
            iEnterpriseRepository.deleteById(enterpriseId);
        }
    }

    @Override
    public void softDeleteById(Integer enterpriseId) {
        iEnterpriseRepository.findById(enterpriseId).ifPresent(enterprise -> {
            enterprise.setSoftDelete(true);
            iEnterpriseRepository.save(enterprise);
        });
    }

    @Override
    public Optional<EnterpriseEntity> findByEmail(String enterprise_email) {
        return Optional.ofNullable(iEnterpriseRepository.findByEmail(enterprise_email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + enterprise_email)));
    }

    @Override
    public Long countAllEnterprises() {
        return iEnterpriseRepository.findAll().stream()
                .filter(enterprise -> enterprise.getSoftDelete() == Boolean.FALSE)
                .count();
    }

    @Override
    public Optional<EnterpriseEntity> activateEnterpriseById(Integer enterpriseId) {
        Optional<EnterpriseEntity> enterpriseOptional = iEnterpriseRepository.findById(enterpriseId);

        if (enterpriseOptional.isPresent()) {
            EnterpriseEntity enterprise = enterpriseOptional.get();
            enterprise.setSoftDelete(false);
            return Optional.of(iEnterpriseRepository.save(enterprise));
        }

        return Optional.empty();
    }

    @Override
    public Optional<EnterpriseEntity> findByPhone(String phone) {
        return Optional.ofNullable(iEnterpriseRepository.findByPhone(phone)
                .orElseThrow(() -> new EntityNotFoundException("Enterprise not found with phone: " + phone)));
    }

    @Override
    public Optional<EnterpriseEntity> findByRut(String rut) {
        return Optional.ofNullable(iEnterpriseRepository.findByRut(rut)
                .orElseThrow(() -> new EntityNotFoundException("Enterprise not found with rut: " + rut)));
    }

    @Override
    public Optional<EnterpriseEntity> findByName_enterprise(String name) {
        return Optional.ofNullable(iEnterpriseRepository.findByName_enterprise(name)
                .orElseThrow(() -> new EntityNotFoundException("Enterprise not found with name " + name)));
    }

}
