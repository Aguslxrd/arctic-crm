package com.arcticnode.crm.Repository.Admin;

import com.arcticnode.crm.Entities.LoggingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoggingRepository extends JpaRepository<LoggingEntity, Integer> {
}
