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
import com.api.Models.Tag;
import com.api.Repository.TagRepository;

@Service
public class TagService {

	private TagRepository tagRepository;
	@Autowired
	public TagService(TagRepository tagRepository) {
		super();
		this.tagRepository = tagRepository;
	}
	
	//get All
	public ResponseEntity<List<Tag> >getAlltags(Integer offset, Integer limit, String sortBy)
		{
			/* this function return all the articles
			 * filterd by 
			 * 			offset the number of page
			 * 			limit the number of tags in page
			 * 			sort the colume that we will use to sort the tags
			 * */
		        
			PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
		   
		    try {
		    	List<Tag> allTags= this.tagRepository.findAll(pageable).getContent();
		    	if(allTags.isEmpty()) {
		    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    	}
		    	return new ResponseEntity<List<Tag>>(allTags,HttpStatus.OK);
				
			} catch (Exception e) {
				 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
		       
		}
	//get one
	public Tag retrieveTag(long id) {
			Optional<Tag> tag= this.tagRepository.findById(id);
			//if (!article.isPresent()){throw new ArticleNotFoundException("id-" + id);}
			return tag.get();
		}
	
	//delete 
	
	public void deleteTag( long id) {
			// this function delete an article by using it id
			this.tagRepository.deleteById(id);
		}
	
	
	// crerate
	public ResponseEntity<Object> createTag( Tag tag) {
			/*this funcion creat article
			 * it take arctice and save it to database
			 * */
			Date now = new Date(0); 
			tag.setCreatedAt(now);tag.setUpdatedAt(now);
			Tag savedTag= this.tagRepository.save(tag);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedTag.getId()).toUri();

			return ResponseEntity.created(location).build();

		}
	

	//update
	
	public ResponseEntity<Object> updateTAg( Tag tag, long id) {

			Optional<Tag> tagOptional = this.tagRepository.findById(id);

			if (!tagOptional.isPresent())
				return ResponseEntity.notFound().build();

			tag.setId(id);
			
			this.tagRepository.save(tag);

			return ResponseEntity.noContent().build();
		}
		
		
	
	
}
