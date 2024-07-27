package com.arcticnode.crm.LogUtils;

import com.arcticnode.crm.Config.AppConfig;
import com.arcticnode.crm.Entities.LoggingEntity;
import com.arcticnode.crm.Services.ILoggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingUtils {

    private final ILoggingService loggingService;
    private final AppConfig appConfig;

    public void logAction(String action, String details) {
        try {
            Integer currentUserId = appConfig.getAuthenticatedUserId();
            loggingService.setLog(currentUserId, action, details);
        } catch (Exception e) {
            log.error("Error logging action: {}", action, e);
        }
    }

    public List<LoggingEntity> getLogs() {
        try {
            return loggingService.getLogs();
        } catch (Exception e) {
            log.error("Error retrieving logs", e);
            return null;
        }
    }

}