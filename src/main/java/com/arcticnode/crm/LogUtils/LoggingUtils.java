package com.arcticnode.crm.LogUtils;

import com.arcticnode.crm.Config.AppConfig;
import com.arcticnode.crm.Services.ILoggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
}