package com.api.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		
		 
		try {
			List<User> allUsers = this.userService.getAllUsers(offset, limit, sortBy);
			if(allUsers.isEmpty()) {{return new ResponseEntity<>(HttpStatus.NO_CONTENT);}}
			ResponseEntity<List<User> > response =new ResponseEntity<>(allUsers,HttpStatus.OK);
			return response ; 
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
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
		
		try {
			User savedUser =this.userService.createUser(user);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();	
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {

		User updateUser= this.userService.updateUser(user, id);
		return ResponseEntity.noContent().build();
	}
	
		
}
