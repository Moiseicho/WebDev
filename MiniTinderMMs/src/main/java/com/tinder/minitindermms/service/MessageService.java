package com.tinder.minitindermms.service;

import com.tinder.minitindermms.entities.MatchEntity;
import com.tinder.minitindermms.entities.MessageEntity;
import com.tinder.minitindermms.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MatchService matchService;

    public ResponseEntity<String> sendMessage(MessageEntity messageEntity) {
        if(!matchService.getConfirmedMatches(messageEntity.getSenderId()).contains(messageEntity.getRecipientId()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't message unmatched users!");
        if(messageEntity.getSenderId() == null || messageEntity.getRecipientId() == null || messageEntity.getContent() == null || messageEntity.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid message");
        }
        messageEntity.setSentAt(java.time.LocalDateTime.now());
        messageRepository.save(messageEntity);
        return ResponseEntity.status(HttpStatus.OK).body("Message sent");
    }

    public List<MessageEntity> getMessages(Long currentUser, Long otherUser) {
        if(!matchService.getConfirmedMatches(currentUser).contains(otherUser))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't message unmatched users!");
        List<MessageEntity> messages = messageRepository.findBySenderIdAndRecipientId(currentUser, otherUser);
        messages.addAll(messageRepository.findBySenderIdAndRecipientId(otherUser, currentUser));
        if(messages.isEmpty()) return null;
        messages.sort((m1, m2) -> m2.getSentAt().compareTo(m1.getSentAt()));
        return messages;
    }

    public List<Long> getRecipientIds(Long currentUser) {
        List<Long> userList = messageRepository.findBySenderId(currentUser)
                .stream()
                .map(MessageEntity::getRecipientId)
                .collect(Collectors.toList());
        userList.addAll(messageRepository.findByRecipientId(currentUser)
                .stream()
                .map(MessageEntity::getSenderId)
                .toList()
        );
        userList = userList.stream().distinct().collect(Collectors.toList());
        return userList;
    }
}
