package com.tynuser.controller;

import com.tynuser.model.ChatMessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {

    /**
     * Handles a chat message and broadcasts it to all connected clients.
     *
     * @param chatMessageDTO The incoming chat message.
     * @return The same chat message DTO, which will be broadcasted to "/topic/public".
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessageDTO){
        return  chatMessageDTO;
    }

    /**
     * Handles a user joining the chat, sets the username in the session attributes,
     * and broadcasts the user join message to all connected clients.
     *
     * @param chatMessageDTO The incoming chat message containing user information.
     * @param headerAccessor SimpMessageHeaderAccessor for accessing message headers.
     * @return The same chat message DTO, which will be broadcasted to "/topic/public".
     */
    @MessageMapping("/chat.userJoined")
    @SendTo("/topic/public")
    public ChatMessageDTO userJoined(
            @Payload ChatMessageDTO chatMessageDTO,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessageDTO.getSender());
        return  chatMessageDTO;
    }

}
