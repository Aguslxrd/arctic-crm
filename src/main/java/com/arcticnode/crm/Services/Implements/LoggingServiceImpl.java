package com.arcticnode.crm.Services.Implements;

import com.arcticnode.crm.Entities.LoggingEntity;
import com.arcticnode.crm.Repository.Admin.ILoggingRepository;
import com.arcticnode.crm.Services.ILoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggingServiceImpl implements ILoggingService {

    @Autowired
    private ILoggingRepository logRepository;

    @Override
    public void setLog(Integer authId, String action, String details) {
        LoggingEntity log = new LoggingEntity();
        log.setUserId(authId);
        log.setUser_action(action);
        log.setDetails(details);
        logRepository.save(log);
    }

    @Override
    public List<LoggingEntity> getLogs() {
        return logRepository.findAll();
    }
}
