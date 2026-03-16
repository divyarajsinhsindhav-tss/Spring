package com.tss.dynamicDI.controller;

import com.tss.dynamicDI.service.NotificationProcessor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class notify {

    private NotificationProcessor notificationProcessor;

    public notify(NotificationProcessor notificationProcessor) {
        this.notificationProcessor = notificationProcessor;
    }


    @PostMapping("/")
    public void sendNotification(@RequestParam String subject, @RequestParam String body, @RequestParam String type) {
        notificationProcessor.send(subject, body, type);
    }
}
