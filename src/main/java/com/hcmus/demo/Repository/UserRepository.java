package com.hcmus.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcmus.demo.chat.user;

public interface UserRepository extends JpaRepository<user, Integer>{

}
