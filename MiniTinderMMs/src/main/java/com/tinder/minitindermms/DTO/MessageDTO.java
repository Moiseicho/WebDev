package com.tinder.minitindermms.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
public class MessageDTO {
    private Long senderId;
    private Long recipientId;
    private String content;
}
