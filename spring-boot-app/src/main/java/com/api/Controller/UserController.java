package com.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.api.Models.User;
import com.api.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	@GetMapping
	
	 public ResponseEntity<List<User>> getAllUsers(
			 		@RequestParam(defaultValue = "0" ,name="Offset") Integer offset, 
			 		@RequestParam(defaultValue = "20",name="limit") Integer limit,
			 		@RequestParam(defaultValue = "id" ,name="sort") String sortBy) 
	{
		ResponseEntity<List<User> > allUsersResponse = this.userService.getAllUsers(offset, limit, sortBy);
		return allUsersResponse  ; 
	}
	

	@GetMapping("/{id}")
	public User retrieveUser(@PathVariable long id) {
		
		return this.userService.retrieveUser(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable long id) {
		this.userService.deleteUser(id);
	}
	
	@PostMapping("/" )
	
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		

		return this.userService.createUser(user);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {

		return this.userService.updateUser(user, id);
	}
}
