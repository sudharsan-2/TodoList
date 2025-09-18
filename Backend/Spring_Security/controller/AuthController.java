package com.example.Spring_Security.controller;

import java.util.Map;
import com.example.Spring_Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_Security.Repository.UserRepo;
import com.example.Spring_Security.Service.UserService;
import com.example.Spring_Security.Utils.JwtUtil;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/auth")
public class AuthController {
	private final UserService userService;
	private final UserRepo userRepo;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	

    @Autowired
    public AuthController(UserService userService, UserRepo userRepo, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body) {
	
		String username=body.get("username");
		String email=body.get("email");
		String password=body.get("password");
		
		if(userRepo.findByEmail(email).isPresent()) {
			
			return new ResponseEntity<>("Email already Exists",HttpStatus.CONFLICT);
		}
		userService.createUser(
				User.builder()
				.username(username)
				.email(email)
				.password(passwordEncoder.encode(password)) //to encode the password
				.build());
		return new ResponseEntity<>("Successfully Registered",HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body) {
		String email=body.get("email");
		String password=body.get("password");
		
		var userOptional=userRepo.findByEmail(email);
		if(userOptional.isEmpty()) {
			return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);	
		}
		
			User user=(User) userOptional.get();
			if (!passwordEncoder.matches(password, user.getPassword())) {
			    return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
			}

			String token =jwtUtil.generateTokens(email);
			return ResponseEntity.ok(Map.of("token",token,"username",user.getUsername()));
	}
}
