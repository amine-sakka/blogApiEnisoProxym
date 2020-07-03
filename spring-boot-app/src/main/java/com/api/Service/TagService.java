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
	public List<Tag> getAlltags(Integer offset, Integer limit, String sortBy)
		{
		
		   PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
		   List<Tag> allTags= this.tagRepository.findAll(pageable).getContent();
		   return allTags;
		    	
				  
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
	public Tag createTag( Tag tag) {
			
			Date now = new Date(0); 
			tag.setCreatedAt(now);tag.setUpdatedAt(now);
			Tag savedTag= this.tagRepository.save(tag);

			
			return savedTag;

		}
	

	//update
	
	public  Optional<Tag> updateTAg( Tag tag, long id) {

			Optional<Tag> tagOptional = this.tagRepository.findById(id);
			tag.setId(id);			
			return tagOptional;
		}
		
		
	
	
}
