package com.hcmus.demo.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmus.demo.DTO.userDTO;
import com.hcmus.demo.Service.UserService;

@RestController
@RequestMapping("/user")
public class userAPI {
     
	@Autowired
	private UserService ser;
	
	@PostMapping
	public userDTO adduser(@RequestBody userDTO user)
	{
		user u=  ser.add(userDTO.dtoToModel(user));
		return userDTO.modelToDTO(ser.add(u));
	}
}
