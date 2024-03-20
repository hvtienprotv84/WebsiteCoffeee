package com.tynuser.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDTO {

    private String Sender;
    private String Content;
    private MessageType type;
}
