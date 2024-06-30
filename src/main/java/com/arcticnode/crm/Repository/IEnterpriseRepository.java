package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEnterpriseRepository extends JpaRepository<EnterpriseEntity, Integer> {

    public Optional<EnterpriseEntity> findByPhone(String phone);
    public Optional<EnterpriseEntity> findByRut(String rut);
    public Optional<EnterpriseEntity> findByName(String name_enterprise);

}
