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
import com.api.Models.Comment;
import com.api.Service.ArticleService;



@RestController
@RequestMapping("/api/articles")
public class ArticleController {

	private ArticleService articleService;
	@Autowired
	public ArticleController(ArticleService articleService) {
		super();
		this.articleService = articleService;
	}
	
	@GetMapping
	
	 public ResponseEntity<List<Article>> getArticles(
			 		@RequestParam(defaultValue = "0" ,name="Offset",required=false) Integer offset, 
			 		@RequestParam(defaultValue = "20",name="limit",required=false) Integer limit,
			 		@RequestParam(defaultValue = "id" ,name="sort",required=false) String sortBy,
			 		@RequestParam(defaultValue = "allAuthores" ,name="author",required=false) String author,
			 		@RequestParam(defaultValue = "allTags" ,name="tag",required=false) String tag) 
	{
		try {
			List<Article> allArticles = this.articleService.getAllArticles(offset, limit, sortBy,author,tag);
			if(allArticles.isEmpty()) {{return new ResponseEntity<>(HttpStatus.NO_CONTENT);}}
			ResponseEntity<List<Article> > response =new ResponseEntity<>(allArticles,HttpStatus.OK);
			return response ; 
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	@GetMapping("/{id}")
	public Article getArticle(@PathVariable long id) {
		
		return this.articleService.retrieveArticle(id);
	}
	
	@GetMapping("/slug={slug}")
	public ResponseEntity<List<Article>>  getArticleBySlug(@PathVariable String slug) {
		try {
			List< Article> articles = this.articleService.findBySlug(slug);
			if(articles.isEmpty()) {{return new ResponseEntity<>(HttpStatus.NO_CONTENT);}}
			ResponseEntity<List<Article> > response =new ResponseEntity<>(articles,HttpStatus.OK);
			return response ; 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		 
	}
	
	//get comments 
	@GetMapping("/{id}/comments")
	public ResponseEntity<List<Comment>>  getComments(@PathVariable long id) {
		try {
			List< Comment> comments= this.articleService.findAllComments(id);
			if(comments.isEmpty()) {{return new ResponseEntity<>(HttpStatus.NO_CONTENT);}}
			ResponseEntity<List<Comment> > response =new ResponseEntity<>(comments,HttpStatus.OK);
			return response ; 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		 
	}
	
	@PostMapping("/" )
	
	public ResponseEntity<Object> createArticle(@RequestBody Article article) {
		
		try {
			Article savedArticle =this.articleService.createArticle(article);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedArticle.getId()).toUri();	
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
		
	}
	
	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateArticle(@RequestBody Article article, @PathVariable long id) {

		Optional<Article> updateArticl= this.articleService.updateArticle(article, id);
		return ResponseEntity.noContent().build();
	}
	

	@DeleteMapping("/{id}")
	public void deleteArticle(@PathVariable long id) {
		this.articleService.deleteArticle(id);
	}	
	
	
	
	
	
	
	

	
	
}
