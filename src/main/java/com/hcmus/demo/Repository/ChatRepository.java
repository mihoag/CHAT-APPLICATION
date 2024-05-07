package com.hcmus.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmus.demo.chat.ChatMessage;

public interface ChatRepository extends JpaRepository<ChatMessage, Integer>{

}
