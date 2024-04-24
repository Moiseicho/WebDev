package com.example.minitinderns.services;

import com.example.minitinderns.entities.NotificationEntity;
import com.example.minitinderns.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public List<NotificationEntity> getAllByUser(Long userId)
    {
        return notificationRepository.findAllByUserId(userId)
                .stream()
                .sorted(Comparator.comparing(NotificationEntity::getCreatedAt))
                .collect(Collectors.toList());
    }

    public NotificationEntity getById(Long notificationId)
    {
        return notificationRepository.findByNotificationId(notificationId);
    }

    public String getEndpointById(Long notificationId)
    {
        return notificationRepository.findByNotificationId(notificationId).getReferralEndpoint();
    }
    public ResponseEntity<String> deleteById(Long notificationId)
    {
        notificationRepository.deleteById(notificationId);
        return new ResponseEntity<String>("Notification deleted", HttpStatus.OK);
    }

    public NotificationEntity createNotification(NotificationEntity notificationEntity)
    {
        return notificationRepository.save(notificationEntity);
    }

}
