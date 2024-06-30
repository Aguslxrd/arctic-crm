package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.EnterpriseEntity;
import com.arcticnode.crm.Entities.UserEntity;
import com.arcticnode.crm.Repository.IEnterpriseRepository;
import com.arcticnode.crm.Services.IEnterpriseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
    public void deleteById(Integer enterpriseId) {
        if (enterpriseId != 0){
            iEnterpriseRepository.deleteById(enterpriseId);
        }
    }

    @Override
    public Optional<EnterpriseEntity> findByEmail(String enterprise_email) {
        return Optional.ofNullable(iEnterpriseRepository.findByEmail(enterprise_email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + enterprise_email)));
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
    public Optional<EnterpriseEntity> findByName(String name) {
        return Optional.ofNullable(iEnterpriseRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Enterprise not found with name " + name)));
    }

}
