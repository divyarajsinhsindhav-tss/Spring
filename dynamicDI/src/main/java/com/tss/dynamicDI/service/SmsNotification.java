package com.tss.dynamicDI.service;

public class SmsNotification {
    public void sendNotification(String subject, String body) {
        System.out.println(subject + " " + body);
    }
}
