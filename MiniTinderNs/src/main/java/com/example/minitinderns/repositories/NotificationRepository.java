package com.example.minitinderns.repositories;

import com.example.minitinderns.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    NotificationEntity findByNotificationId(Long notificationId);
    List<NotificationEntity> findAllByUserId(Long userId);

}
