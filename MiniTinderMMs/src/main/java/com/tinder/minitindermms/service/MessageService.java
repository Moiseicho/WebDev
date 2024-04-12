package com.tinder.minitindermms.service;

import com.tinder.minitindermms.entities.MatchEntity;
import com.tinder.minitindermms.entities.MessageEntity;
import com.tinder.minitindermms.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public ResponseEntity<String> sendMessage(MessageEntity messageEntity) {
        if(messageEntity.getSenderId() == null || messageEntity.getRecipientId() == null || messageEntity.getContent() == null || messageEntity.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid message");
        }
        messageEntity.setSentAt(java.time.LocalDateTime.now());
        messageRepository.save(messageEntity);
        return ResponseEntity.status(HttpStatus.OK).body("Message sent");
    }

    public List<MessageEntity> getMessages(Long currentUser, Long otherUser) {
        List<MessageEntity> messages = messageRepository.findBySenderIdAndRecipientId(currentUser, otherUser);
        messages.addAll(messageRepository.findBySenderIdAndRecipientId(otherUser, currentUser));
        messages.sort((m1, m2) -> m2.getSentAt().compareTo(m1.getSentAt()));
        return messages;
    }

    public List<Long> getRecipientIds(Long currentUser) {
        List<Long> userList = messageRepository.findRecipientIdBySenderId(currentUser);
        userList.addAll(messageRepository.findSenderIdByRecipientId(currentUser));
        return userList;
    }
}
