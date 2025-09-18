package com.example.Spring_Security.Service;

import com.example.Spring_Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Spring_Security.Repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	

	public User createUser(User user) {
		return userRepo.save(user);
	}


	public  User getById(int id) {
		return userRepo.findById(id).orElse(null);
	}
	
}
