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
	public ResponseEntity<List<User> >getAllUsers(Integer offset, Integer limit, String sortBy)
	{
		/* this function return all the users
		 * filterd by 
		 * 			offset the number of page
		 * 			limit the number of articles in page
		 * 			sort the colume that we will use to sort the Users
		 * */
	        
		PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
	   
	    try {
	    	List<User> allUsers= this.userRepository.findAll(pageable).getContent();
	    	if(allUsers.isEmpty()) {
	    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	}
	    	return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
			
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	       
	}
	
	//get one
	public User retrieveUser(long id) {
			Optional<User> user= this.userRepository.findById(id);
			//if (!article.isPresent()){throw new ArticleNotFoundException("id-" + id);}
			return user.get();
		}
	
	//delete 
	
	public void deleteUser( long id) {
			// this function delete an article by using it id
			this.userRepository.deleteById(id);
		}
	
	// crerate
	public ResponseEntity<Object> createUser( User user) {
			/*this funcion creat article
			 * it take arctice and save it to database
			 * */
			Date now = new Date(0); 
			user.setCreatedAt(now);user.setUpdatedAt(now);
			User savedUser= this.userRepository.save(user);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedUser.getId()).toUri();

			return ResponseEntity.created(location).build();

		}
	

	//update
	
	public ResponseEntity<Object> updateUser( User user, long id) {

		Optional<User> userOptional = this.userRepository.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		this.userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
		
	
	
}
