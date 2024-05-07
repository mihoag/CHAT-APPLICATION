package com.hcmus.demo.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.hcmus.demo.DTO.messDTO;
import com.hcmus.demo.Service.ChatService;
import com.hcmus.demo.Service.UserService;

@Controller
public class ChatController {

	@Autowired
	private ChatService  chatser;
	
	@Autowired
	private UserService userSer;
	
	
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public messDTO sendMessage(
            @Payload messDTO chatMessage
    ) {
    	user u = userSer.getById(chatMessage.getIdUser());
    	
    	//System.out.println(u);    	
    	ChatMessage mess = messDTO.dtoToModel(chatMessage,u);
    	chatser.add(mess);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public messDTO addUser(
            @Payload messDTO chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("idUser", chatMessage.getIdUser());
        return chatMessage;
    }
}
