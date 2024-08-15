package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.LoggingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILoggingService {
    public void setLog(Integer authId, String action, String details);
    public Page<LoggingEntity> getLogs(Pageable pageable);

}
