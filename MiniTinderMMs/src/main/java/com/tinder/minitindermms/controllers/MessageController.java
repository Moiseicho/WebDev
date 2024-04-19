package com.tinder.minitindermms.controllers;

import com.tinder.minitindermms.DTO.MessageDTO;
import com.tinder.minitindermms.entities.MessageEntity;
import com.tinder.minitindermms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDTO messageDTO) {
        return messageService.sendMessage(messageDTO);
    }

    @GetMapping("/{recipientId}")
    public List<MessageEntity> getMessages(@RequestBody Long senderId, @PathVariable Long recipientId) {
        return messageService.getMessages(senderId, recipientId);
    }

    @GetMapping("")
    public List<Long> getMessagedUsersIds(@RequestBody Long currentUser) {
        return messageService.getRecipientIds(currentUser);
    }

}
