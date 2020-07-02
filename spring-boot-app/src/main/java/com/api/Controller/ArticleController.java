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
		ResponseEntity<List<Article> > allArticlesResponse = this.articleService.getAllArticles(offset, limit, sortBy,author,tag);
		return allArticlesResponse ; 
	}
	
	
	
	@PostMapping("/" )
	
	public ResponseEntity<Object> createArticle(@RequestBody Article article) {
		

		return this.articleService.createArticle(article);

	}
	
	@GetMapping("/{id}")
	public Article getArticle(@PathVariable long id) {
		
		return this.articleService.retrieveArticle(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateArticle(@RequestBody Article article, @PathVariable long id) {

		return this.articleService.updateArticle(article, id);
	}
	

	@DeleteMapping("/{id}")
	public void deleteArticle(@PathVariable long id) {
		this.articleService.deleteArticle(id);
	}
	
	
	
	
	
	

	
	
}
