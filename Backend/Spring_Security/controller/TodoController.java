package com.example.Spring_Security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring_Security.Service.TodoService;
import com.example.Spring_Security.model.Todo;
import com.example.Spring_Security.model.User;

@CrossOrigin(originPatterns = "http://localhost:5173")
@RestController
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@PostMapping("/addtodo")
	public User addtodo(@RequestBody Todo todo) {
		return todoService.saveTodo(todo);
	}
	
	@PutMapping("/update/{id}")
	public Todo updateTodo(@PathVariable int id,@RequestBody Todo todo) {
		return todoService.updateTodo(id,todo);
	}
		
	@GetMapping("/getAll")
	public List<Todo> fetchAll() {
		return todoService.fetchAll();
	}
	@DeleteMapping("/delete/{id}")
	public String deleteTodo(@PathVariable int id) {
		 todoService.deleteTodo(id);
		 return "Todo is Dleted";
	}
	
	
	
}
