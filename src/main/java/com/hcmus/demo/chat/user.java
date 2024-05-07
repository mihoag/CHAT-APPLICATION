package com.hcmus.demo.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "user")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    
 
    
   
    
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}


  
	public user(Integer id) {
		super();
		this.id = id;
	}

	public user(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		user other = (user) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "user [id=" + id + ", name=" + name + ", image=" + image + ", listMess="  + "]";
	}
}
