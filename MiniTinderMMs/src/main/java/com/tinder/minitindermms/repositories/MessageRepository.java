package com.tinder.minitindermms.repositories;

import com.tinder.minitindermms.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
    List<Long> findRecipientIdBySenderId(Long senderId);
    List<Long> findSenderIdByRecipientId(Long recipientId);
}