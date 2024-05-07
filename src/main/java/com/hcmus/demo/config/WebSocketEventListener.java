package com.hcmus.demo.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.hcmus.demo.DTO.messDTO;
import com.hcmus.demo.chat.ChatMessage;

@Component
public class WebSocketEventListener {

	
    private SimpMessageSendingOperations messagingTemplate;
 
    @Autowired
    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate)
    {
    	this.messagingTemplate = messagingTemplate;
    }
	
	
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Integer idUser = (Integer) headerAccessor.getSessionAttributes().get("idUser");
        if (idUser != null) {
        	var messDto = new messDTO("LEAVE" , idUser);
            messagingTemplate.convertAndSend("/topic/public", messDto);
        }
    }

}
