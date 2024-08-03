package com.arcticnode.crm.Repository;

import com.arcticnode.crm.Entities.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEnterpriseRepository extends JpaRepository<EnterpriseEntity, Integer> {

    Optional<EnterpriseEntity> findByPhone(String phone);
    Optional<EnterpriseEntity> findByRut(String rut);

    @Query("SELECT e FROM EnterpriseEntity e WHERE LOWER(e.name_enterprise) LIKE LOWER(CONCAT('%', :name_enterprise, '%'))")
    Optional<EnterpriseEntity> findByName_enterprise(@Param("name_enterprise") String name_enterprise);

    Optional<EnterpriseEntity> findByEmail(String email);
    public List<EnterpriseEntity> findAllBySoftDeleteFalse();
    public List<EnterpriseEntity> findAllBySoftDeleteTrue();
}