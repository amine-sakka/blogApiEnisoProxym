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
import com.api.Models.Comment;
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
	public ArticleService(ArticleRepository articleRepository, UserRepository userRepository,TagRepository tagRepository) {
		super();
		this.articleRepository = articleRepository;
		this.userRepository=userRepository;
		this.tagRepository=tagRepository;
	}
	
	//get All
	public List<Article> getAllArticles(Integer offset, Integer limit, String sortBy,String authorUserName,String tagText)
	{
		
	    	if(authorUserName.equals("allAuthores") && tagText.equals("allTags"))
	    	{return(this.findAll(offset, limit, sortBy));}
	    	if(!authorUserName.equals("allAuthores") && tagText.equals("allTags"))
	    	{return(this.findByAuthorId(authorUserName,offset, limit, sortBy));}
	    	if(authorUserName.equals("allAuthores") && !tagText.equals("allTags"))
	    	{return(this.findByTagId(tagText, offset, limit, sortBy));}
	    	
	    	if(!authorUserName.equals("allAuthores") && !tagText.equals("allTags"))
	    	{return(this.findAllByTagAndAuthor(tagText, authorUserName, offset, limit, sortBy));}
	    	else {return null;}
	    	
	    	
	    	
		
	       
	}
	
	//get one
	public Article retrieveArticle(long id) {
		Optional<Article> article = this.articleRepository.findById(id);
		return article.get();
	}
	
	//delete 
	
	public void deleteArticle( long id) {
		this.articleRepository.deleteById(id);
	}
	
	// crerate
	public Article createArticle( Article article) {
		
		Date now = new Date(0); 
		article.setCreatedAt(now);article.setUpdatedAt(now);article.setFavoritesCount(0);
		Article savedArticle = this.articleRepository.save(article);

		//URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedArticle.getId()).toUri();

		return savedArticle;

	}
	
	//update
	
	public Optional <Article> updateArticle( Article article, long id) {

		Optional<Article> articleOptional = this.articleRepository.findById(id);
		article.setId(id);
		this.articleRepository.save(article);

		return articleOptional;
	}
	//find by slug
	public List<Article> findBySlug(String slug) {
		 
			 List<Article> articles= this.articleRepository.findBySlug(slug);
			 if(articles.isEmpty()) {return null;}
			 return articles;
		
		
	 }
	
 
	public List<Article> findByAuthorId(String authorName,Integer offset, Integer limit, String sortBy)
		{
			try {
				PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
				User author=this.userRepository.findByUserName(authorName).get(0);
				List< Article >articles =this.articleRepository.findByAuthorId(author.getId(),pageable);
				if(articles.isEmpty()) {return articles;}
				return (articles);
				
			} catch (Exception e) {
				 return null;
			}
		 	
		 
		}
	
	public List<Article>  findByTagId(String tagText,Integer offset, Integer limit, String sortBy)
	{
		
		try {
			PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
			Tag tag=this.tagRepository.findByTagText(tagText).get(0);
			List< Article >articles =this.articleRepository.findByTagId(tag.getId(),pageable);
			if(articles.isEmpty()) {return null;}
			return (articles);
			
		} catch (Exception e) {
			return null;
		}
		
	 	
	 
	}
	
	
	public List<Article> findAllByTagAndAuthor(String tagText,String authorName,Integer offset, Integer limit, String sortBy)
	{
	    try {
	    	PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
	    	Tag tag=this.tagRepository.findByTagText(tagText).get(0);
	    	User author=this.userRepository.findByUserName(authorName).get(0);
	    	List<Article> allArticles = allArticles= this.articleRepository.findByTagIdAndAuthorId(tag.getId(), author.getId(), pageable);
	    	
	    	if(allArticles.isEmpty()) {
	    		return allArticles;
	    	}
	    	return allArticles;
			
		} catch (Exception e) {
			 return null;
		}
	       
	}
	
	public List<Article>findAll(Integer offset, Integer limit, String sortBy)
	{
	        
	    try {
	    	PageRequest pageable = PageRequest.of(offset, limit, Sort.by(sortBy));
	    	List<Article> allArticles = allArticles= this.articleRepository.findAll(pageable).getContent();
	    	
	    	if(allArticles.isEmpty()) {
	    		return allArticles;
	    	}
	    	return allArticles;
			
		} catch (Exception e) {
			 return null;
		}
	       
	}
	
	public List<Comment> findAllComments(long id)
	{
	   
	    	
	    	Optional<Article> article =this.articleRepository.findById(id);
	    	List<Comment> comments=article.get().getComments();
	    	return comments;
	    	
	    	
			
		
	       
	}
		 			
	
	
	
}