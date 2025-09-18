package com.example.Spring_Security.Repository;

import java.util.Optional;

import com.example.Spring_Security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
 