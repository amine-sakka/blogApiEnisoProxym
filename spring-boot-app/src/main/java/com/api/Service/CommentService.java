package com.api.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.api.Models.Comment;
import com.api.Repository.CommentRepository;

@Service
public class CommentService {
	private CommentRepository commentRepository;
	@Autowired
	public CommentService(CommentRepository commentRepository) {
		super();
		this.commentRepository = commentRepository;
	}
	
	//get All
	public List<Comment> getAllComments(Integer offset, Integer limit, String sortBy)
	{
		
	        
		PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
	    List<Comment> allComments= this.commentRepository.findAll(pageable).getContent();
	    return (allComments);
			
		
	       
	}
	
	//get one
	public Comment retrieveComment(long id) {
			Optional<Comment> comment= this.commentRepository.findById(id);
			return comment.get();
		}
	
	public void deleteComment( long id) {
		// this function delete an article by using it id
		this.commentRepository.deleteById(id);
	}
	
	// crerate
	public Comment createComment( Comment comment) {
			
			Date now = new Date(0); 
			comment.setCreatedAt(now);comment.setUpdatedAt(now);
			Comment savedComment= this.commentRepository.save(comment);

			return savedComment ;

		}
	
	
	public Comment updateComment( Comment comment, long id) {

		Optional<Comment> commentOptional = this.commentRepository.findById(id);

		comment.setId(id);
		
		this.commentRepository.save(comment);

		return commentOptional.get();
	}
		
	
}
