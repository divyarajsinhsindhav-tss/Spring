package com.tss.dynamicDI.service;

import lombok.RequiredArgsConstructor;
import java.util.Map;

@RequiredArgsConstructor
public class NotificationProcessor {
    private final Map<String, NotificationService> notificationServiceMap;

    public void send(String type, String subject, String body) {
        if (notificationServiceMap.containsKey(type)) {
            NotificationService notificationService = notificationServiceMap.get(type);
            notificationService.sendNotification(subject, body);
        }
    }

}
