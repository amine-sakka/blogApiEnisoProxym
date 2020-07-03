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

import com.api.Models.Article;
import com.api.Models.Tag;
import com.api.Service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

	private TagService tagService;

	@Autowired
	public TagController(TagService tagService) {
		super();
		this.tagService = tagService;
	}
	
	@GetMapping
	public ResponseEntity<List<Tag>> getAllTags(
			 		@RequestParam(defaultValue = "0" ,name="Offset") Integer offset, 
			 		@RequestParam(defaultValue = "20",name="limit") Integer limit,
			 		@RequestParam(defaultValue = "id" ,name="sort") String sortBy) 
	{
		try {
			List<Tag> tags =this.tagService.getAlltags(offset, limit, sortBy);
			if(tags.isEmpty()) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
			ResponseEntity<List<Tag> > response =new ResponseEntity<>(tags,HttpStatus.OK);
			return response ;
 		} catch (Exception e) {
 			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}	
	
	
	@GetMapping("/{id}")
	public Tag retrieveTag(@PathVariable long id) {
		
		return this.tagService.retrieveTag(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTag(@PathVariable long id) {
		this.tagService.deleteTag(id);
	}
	
	@PostMapping("/" )
	public ResponseEntity<Object> createTag(@RequestBody Tag tag) {
		try {
			Tag savedTag =this.tagService.createTag(tag);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTag.getId()).toUri();	
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTag(@RequestBody Tag tag, @PathVariable long id) {

		Optional<Tag> updateTag= this.tagService.updateTAg(tag, id);
		return ResponseEntity.noContent().build();
	}


	
}
