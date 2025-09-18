package com.example.Spring_Security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Spring_Security.model.Todo;

public  interface TodoRepository extends JpaRepository<Todo, Integer>{

}
