package com.tss.dynamicDI.service;

public class EmailNotification implements NotificationService {
    public void sendNotification(String subject, String body) {
        System.out.println(subject + " " + body);
    }
}
