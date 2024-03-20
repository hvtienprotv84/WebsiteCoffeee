package com.tynuser.config;

import com.tynuser.model.ChatMessageDTO;
import com.tynuser.model.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListner {

    private  final SimpMessageSendingOperations messageSendingOperations;

    /**
     * Event listener method to handle WebSocket disconnect events.
     *
     * @param event SessionDisconnectEvent representing a WebSocket session disconnect.
     */
    @EventListener
    public void handleWebSocketDisconnectListner(SessionDisconnectEvent event){
        // Extract StompHeaderAccessor from the event message.
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("username");
        if (username != null){
            // Create a ChatMessageDTO representing a user leaving the chat.
            var chatMessageDTO = ChatMessageDTO.builder()
                    .type(MessageType.LEAVE)
                    .Sender(username)
                    .build();
            // Send the leave message to the "/topic/public" destination.
            messageSendingOperations.convertAndSend("/topic/public",chatMessageDTO);
        }
    }
}
