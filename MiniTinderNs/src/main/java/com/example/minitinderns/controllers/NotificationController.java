package com.example.minitinderns.controllers;

import com.example.minitinderns.entities.NotificationEntity;
import com.example.minitinderns.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationEntity>> getAllByUser(@PathVariable Long userId) {
        List<NotificationEntity> notifications = notificationService.getAllByUser(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationEntity> getById(@PathVariable Long notificationId) {
        NotificationEntity notification = notificationService.getById(notificationId);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @GetMapping("/{notificationId}/endpoint")
    public ResponseEntity<String> getEndpointById(@PathVariable Long notificationId) {
        String endpoint = notificationService.getEndpointById(notificationId);
        return new ResponseEntity<>(endpoint, HttpStatus.OK);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteById(@PathVariable Long notificationId) {
        return notificationService.deleteById(notificationId);
    }

    @PostMapping("/")
    public ResponseEntity<NotificationEntity> createNotification(@RequestBody NotificationEntity notificationEntity)
    {
        return new ResponseEntity<>(notificationService.createNotification(notificationEntity), HttpStatus.OK);
    }

}
