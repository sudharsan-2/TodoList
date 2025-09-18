package com.example.Spring_Security.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.Spring_Security.Repository.TodoRepository;
import com.example.Spring_Security.Repository.UserRepo;
import com.example.Spring_Security.model.Todo;
import com.example.Spring_Security.model.User;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	@Autowired private UserRepo userrepo;
	
	
	//addtodo
	public User saveTodo(Todo todo) {
	String email = SecurityContextHolder.getContext().getAuthentication().getName();
	User user=userrepo.findByEmail(email).orElse(null);
	todo.setUser(user);
	user.getTodos().add(todo);
		
	return userrepo.save(user);
	}

	//update
	public Todo updateTodo(int id,Todo newtodo) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userrepo.findByEmail(email).orElse(null);
		Todo todo=todoRepository.findById(id).orElse(null);
		todo.setTask(newtodo.getTask());
		todo.setChecked(newtodo.isChecked());
		return todoRepository.save(todo);
	}
	//fetchAll
	public List<Todo> fetchAll(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(email+"   email ===");
		User user=userrepo.findByEmail(email).orElse(null);
		return user.getTodos();
	}
	//Delete
	public void deleteTodo(int id) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userrepo.findByEmail(email).orElse(null);
		Todo todo=todoRepository.findById(id).orElse(null);
		user.getTodos().remove(todo);
		todo.setUser(null);
		todoRepository.delete(todo);
		
	}
}
