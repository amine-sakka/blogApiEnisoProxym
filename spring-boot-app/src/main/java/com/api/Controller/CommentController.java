package com.api.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.Models.Comment;
import com.api.Service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private CommentService commentService;
	@Autowired
	public CommentController (CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	@GetMapping
	
	 public ResponseEntity<List<Comment>> getAllUsers(
			 		@RequestParam(defaultValue = "0" ,name="Offset") Integer offset, 
			 		@RequestParam(defaultValue = "20",name="limit") Integer limit,
			 		@RequestParam(defaultValue = "id" ,name="sort") String sortBy) 
	{
		
		 
		try {
			List<Comment> allcomments= this.commentService.getAllComments(offset, limit, sortBy);
			if(allcomments.isEmpty()) {{return new ResponseEntity<>(HttpStatus.NO_CONTENT);}}
			ResponseEntity<List<Comment> > response =new ResponseEntity<>(allcomments,HttpStatus.OK);
			return response ; 
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/{id}")
	public Comment retrieveUser(@PathVariable long id) {
		
		return this.commentService.retrieveComment(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteComment(@PathVariable long id) {
		this.commentService.deleteComment(id);
	}
	@PostMapping("/" )
	
	public ResponseEntity<Object> createComment(@RequestBody Comment comment) {
		
		try {
			Comment savedComment =this.commentService.createComment(comment);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedComment.getId()).toUri();	
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}
	
}
