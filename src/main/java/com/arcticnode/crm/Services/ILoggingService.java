package com.arcticnode.crm.Services;

import com.arcticnode.crm.Entities.LoggingEntity;

import java.util.List;

public interface ILoggingService {
    public void setLog(Integer authId, String action, String details);
    public List<LoggingEntity> getLogs();

}
