package com.hcmus.demo.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmus.demo.DTO.messDTO;
import com.hcmus.demo.Service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatAPI {
	
	 @Autowired
	 private ChatService ser;
	
	 @GetMapping
     public List<messDTO> findAll()
     {
    	 return messDTO.listModelToDTO(ser.getAll());
     }
}
