package com.hcmus.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmus.demo.Repository.ChatRepository;
import com.hcmus.demo.chat.ChatMessage;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ChatService {
     @Autowired
     private ChatRepository repo;
     
     public List<ChatMessage> getAll()
     {
    	return repo.findAll();
     }
     
     public ChatMessage add(ChatMessage mess)
     {
       return repo.save(mess);
     }
}
