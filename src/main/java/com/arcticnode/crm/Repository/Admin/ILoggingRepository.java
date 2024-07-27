package com.arcticnode.crm.Repository.Admin;

import com.arcticnode.crm.Entities.LoggingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoggingRepository extends JpaRepository<LoggingEntity, Integer> {
}
