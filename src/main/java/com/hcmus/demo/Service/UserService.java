package com.hcmus.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmus.demo.Repository.UserRepository;
import com.hcmus.demo.chat.user;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;
    
    public user add(user u)
    {
    	return repo.save(u);
    }
    
    public user getById(Integer id)
    {
    	return repo.findById(id).get();
    }
    
    public void delete(user u)
    {
    	repo.delete(u);
    }
}
