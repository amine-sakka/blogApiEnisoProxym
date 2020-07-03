package com.api.Service;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.Models.Article;
import com.api.Models.User;
import com.api.Repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository ;
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	//get All
	public List<User> getAllUsers(Integer offset, Integer limit, String sortBy)
	{
		
	        
		PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
	    List<User> allUsers= this.userRepository.findAll(pageable).getContent();
	    return (allUsers);
			
		
	       
	}
	
	//get one
	public User retrieveUser(long id) {
			Optional<User> user= this.userRepository.findById(id);
			return user.get();
		}
	
	//delete 
	
	public void deleteUser( long id) {
			// this function delete an article by using it id
			this.userRepository.deleteById(id);
		}
	
	// crerate
	public User createUser( User user) {
			/*this funcion creat article
			 * it take arctice and save it to database
			 * */
			Date now = new Date(0); 
			user.setCreatedAt(now);user.setUpdatedAt(now);
			User savedUser= this.userRepository.save(user);

			return savedUser ;

		}
	

	//update
	
	public User updateUser( User user, long id) {

		Optional<User> userOptional = this.userRepository.findById(id);

		user.setId(id);
		
		this.userRepository.save(user);

		return userOptional.get();
	}
		
	
	
}
