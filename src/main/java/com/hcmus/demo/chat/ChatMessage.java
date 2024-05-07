package com.hcmus.demo.chat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "message")
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String type;
    private String content;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private user user;
    
  
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
   	


	public ChatMessage(String type, String content, user user) {
		super();
		this.type = type;
		this.content = content;
		this.user = user;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public user getUser() {
		return user;
	}




	public void setUser(user user) {
		this.user = user;
	}




	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
