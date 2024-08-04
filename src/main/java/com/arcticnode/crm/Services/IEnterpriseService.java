package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.EnterpriseEntity;
import com.arcticnode.crm.Entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IEnterpriseService {

    public EnterpriseEntity save(EnterpriseEntity enterprise);
    @Transactional(readOnly = true)
    public Optional<EnterpriseEntity> findById(Integer id);
    @Transactional(readOnly = true)
    public List<EnterpriseEntity> findAll();
    @Transactional(readOnly = true)
    public Page<EnterpriseEntity> findAllBySoftDeleteFalse(Pageable pageable);
    @Transactional(readOnly = true)
    public List<EnterpriseEntity> findAllBySoftDeleteTrue();
    public void deleteById(Integer enterpriseId);
    public void softDeleteById(Integer enterpriseId);
    @Transactional(readOnly = true)
    public Optional<EnterpriseEntity> findByPhone(String phone);
    @Transactional(readOnly = true)
    public Optional<EnterpriseEntity> findByRut(String rut);
    @Transactional(readOnly = true)
    public Optional<EnterpriseEntity> findByName_enterprise(String name);
    public Optional<EnterpriseEntity> findByEmail(String enterprise_email);
    public Long countAllEnterprises();
    public Optional<EnterpriseEntity> activateEnterpriseById(Integer enterpriseId);
}
