package com.tinder.minitindermms.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Getter
@Setter
@Data
public class MessageEntity {
    @PrimaryKey
    private UUID messageId;
    private Long senderId;
    private Long recipientId;
    private String content;
    private LocalDateTime sentAt;

    public MessageEntity(Long senderId, Long recipientId, String content)
    {
        messageId = UUID.randomUUID();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        sentAt = LocalDateTime.now();
    }

}