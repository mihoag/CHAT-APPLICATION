package com.hcmus.demo.DTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcmus.demo.Repository.UserRepository;
import com.hcmus.demo.chat.ChatMessage;
import com.hcmus.demo.chat.user;

public class messDTO {
	private Integer id;
    private String type;
    private String content;
    private Integer idUser;
    private String name;
    private String image;
	
    
	
	public messDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public messDTO(Integer id, String type, String content, Integer idUser, String name, String image) {
		super();
		this.id = id;
		this.type = type;
		this.content = content;
		this.idUser = idUser;
		this.name = name;
		this.image = image;
	}
	
	
	
	public messDTO(String type, Integer idUser) {
		this.type = type;
		this.idUser = idUser;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
	
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public static messDTO modelToDTO(ChatMessage mess)
	{
	   	return new messDTO(mess.getId(), mess.getType(), mess.getContent(), mess.getUser().getId(), mess.getUser().getName(), mess.getUser().getImage());
	}
	
	public static List<messDTO> listModelToDTO(List<ChatMessage> lsMess)
	{
		List<messDTO> rs = new ArrayList<>();
		lsMess.forEach((message) -> {
			rs.add(modelToDTO(message));
		});
		return rs;
	}
	
	public static ChatMessage dtoToModel(messDTO dto, user u)
	{
		ChatMessage c = new ChatMessage(dto.getType(), dto.getContent(), u);
		return c;
	}
}
