package com.api.Models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	private String tagText;
	private Date createdAt;
	private Date updatedAt;
	
	
	@ManyToMany()
	@JoinTable(name = "ARTICLE_TAG", joinColumns = { @JoinColumn(name = "TAG_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "ARTICLE_ID") })
	@JsonIgnore
	
	private Set<Article> articles;
	public Tag(Long id, String tagText, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.tagText = tagText;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Tag() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTagText() {
		return tagText;
	}
	public void setTagText(String tagText) {
		this.tagText = tagText;
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
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
	

}
