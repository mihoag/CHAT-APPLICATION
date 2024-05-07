package com.hcmus.demo.DTO;

import java.util.ArrayList;

import com.hcmus.demo.chat.ChatMessage;
import com.hcmus.demo.chat.user;

public class userDTO {
	 private Integer id;
	 private String name;
	 private String image;
	 
	 
	public userDTO(Integer id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}
	
	
	public userDTO(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}


	public userDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public static userDTO modelToDTO(user u)
	{
		return new userDTO(u.getId(), u.getName(), u.getImage());
	}
	public static user dtoToModel(userDTO dto)
	{
		return new user(dto.getName(), dto.getImage());
	}
	
}
