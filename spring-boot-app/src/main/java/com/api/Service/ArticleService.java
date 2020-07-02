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
import com.api.Models.User;
import com.api.Repository.ArticleRepository;
import com.api.Repository.TagRepository;
import com.api.Repository.UserRepository;


@Service
public class ArticleService {

	private ArticleRepository articleRepository;
	private UserRepository userRepository;
	private TagRepository tagRepository;

	@Autowired
	public ArticleService(ArticleRepository articleRepository, UserRepository userRepository,
			 TagRepository tagRepository) {
		super();
		this.articleRepository = articleRepository;
		this.userRepository=userRepository;
		this.tagRepository=tagRepository;
	}
	
	//get All
	public ResponseEntity<List<Article> >getAllArticles(Integer offset, Integer limit, String sortBy,String authorUserName,String tagText)
	{
		/* this function return all the articles
		 * filterd by 
		 * 			offset the number of page
		 * 			limit the number of articles in page
		 * 			sort the colume that we will use to sort the articles
		 * */
	        
	    try {
	    	if(authorUserName.equals("allAuthores") && tagText.equals("allTags"))
	    	{return(this.findAll(offset, limit, sortBy));}
	    	if(!authorUserName.equals("allAuthores") && tagText.equals("allTags"))
	    	{return(this.findByAuthorId(authorUserName,offset, limit, sortBy));}
	    	if(authorUserName.equals("allAuthores") && !tagText.equals("allTags"))
	    	{return(this.findByTagId(tagText, offset, limit, sortBy));}
	    	
	    	if(!authorUserName.equals("allAuthores") && !tagText.equals("allTags"))
	    	{return(this.findAllByTagAndAuthor(tagText, authorUserName, offset, limit, sortBy));}
	    	else {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
	    	
	    	
	    	
			
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	       
	}
	
	//get one
	public Article retrieveArticle(long id) {
		Optional<Article> article = this.articleRepository.findById(id);
		//if (!article.isPresent()){throw new ArticleNotFoundException("id-" + id);}
		return article.get();
	}
	
	//delete 
	
	public void deleteArticle( long id) {
		// this function delete an article by using it id
		this.articleRepository.deleteById(id);
	}
	
	// crerate
	public ResponseEntity<Object> createArticle( Article article) {
		/*this funcion creat article
		 * it take arctice and save it to database
		 * */
		Date now = new Date(0); 
		article.setCreatedAt(now);article.setUpdatedAt(now);article.setFavoritesCount(0);
		Article savedArticle = this.articleRepository.save(article);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedArticle.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	//update
	
	public ResponseEntity<Object> updateArticle( Article article, long id) {

		Optional<Article> articleOptional = this.articleRepository.findById(id);

		if (!articleOptional.isPresent())
			return ResponseEntity.notFound().build();

		article.setId(id);
		
		this.articleRepository.save(article);

		return ResponseEntity.noContent().build();
	}
	//find by slug
	public ResponseEntity<List<Article>> findBySlug(String slug) {
		 try {
			 List<Article> articles= this.articleRepository.findBySlug(slug);
			 if(articles.isEmpty()) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
			 return new ResponseEntity<>(articles, HttpStatus.OK);
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		 
		
	 }
	
 
	public ResponseEntity<List<Article>> findByAuthorId(String authorName,Integer offset, Integer limit, String sortBy)
		{
			try {
				PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
				User author=this.userRepository.findByUserName(authorName).get(0);
				List< Article >articles =this.articleRepository.findByAuthorId(author.getId(),pageable);
				if(articles.isEmpty()) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
				return new ResponseEntity<>(articles, HttpStatus.OK);
				
			} catch (Exception e) {
				 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
		 	
		 
		}
	
	public ResponseEntity<List<Article>> findByTagId(String tagText,Integer offset, Integer limit, String sortBy)
	{
		
		try {
			PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
			Tag tag=this.tagRepository.findByTagText(tagText).get(0);
			List< Article >articles =this.articleRepository.findByTagId(tag.getId(),pageable);
			if(articles.isEmpty()) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
			return new ResponseEntity<>(articles, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		
	 	
	 
	}
	
	
	public ResponseEntity<List<Article> >findAllByTagAndAuthor(String tagText,String authorName,Integer offset, Integer limit, String sortBy)
	{
	    try {
	    	PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
	    	Tag tag=this.tagRepository.findByTagText(tagText).get(0);
	    	User author=this.userRepository.findByUserName(authorName).get(0);
	    	List<Article> allArticles = allArticles= this.articleRepository.findByTagIdAndAuthorId(tag.getId(), author.getId(), pageable);
	    	
	    	if(allArticles.isEmpty()) {
	    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	}
	    	return new ResponseEntity<List<Article>>(allArticles,HttpStatus.OK);
			
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	       
	}
	
	public ResponseEntity<List<Article> >findAll(Integer offset, Integer limit, String sortBy)
	{
	        
	    try {
	    	PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
	    	List<Article> allArticles = allArticles= this.articleRepository.findAll(pageable).getContent();
	    	
	    	if(allArticles.isEmpty()) {
	    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    	}
	    	return new ResponseEntity<List<Article>>(allArticles,HttpStatus.OK);
			
		} catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	       
	}
		 			
	
	
	
}