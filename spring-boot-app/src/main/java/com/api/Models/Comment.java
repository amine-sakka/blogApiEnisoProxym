package com.api.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	private String body;
	private Date createdAt;
	private Date updatedAt;
	

	@ManyToOne
	private User commentAuthor ;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Article article ;
	
	

	public Comment(Long id, String body, Date createdAt, Date updatedAt, User commentAuthor, Article article) {
		super();
		this.id = id;
		this.body = body;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.commentAuthor = commentAuthor;
		this.article = article;
	}

	public Comment() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getCommentAuthor() {
		return commentAuthor;
	}

	public void setCommentAuthor(User commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	
}
