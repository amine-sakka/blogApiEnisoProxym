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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class User {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private Date createdAt;
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

	private Date updatedAt;
	@JsonIgnore
	private String password;
	
	
	
	
	@OneToMany(mappedBy = "author")
	@JsonBackReference
	private List<Article> articles;
	
	@JsonIgnore
	@OneToMany(mappedBy = "commentAuthor")
	private List <Comment> comments;
	/*@ManyToMany( mappedBy = "usersHowLikeThis",targetEntity=Article.class,fetch = FetchType.LAZY)
	private Set <Article> favouriteArticles;*/
	 
	@ManyToMany(fetch = FetchType.EAGER ,targetEntity = Article.class)
	@JoinTable(name = "FAVOURITES", joinColumns = { @JoinColumn(name = "ARTICLE_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "USER_ID") })

	private Set<Article> favouriteArticles;
	
	
  
	

	public User(Long id,String userName, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.userName=userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<Article> getFavourites() {
		return favouriteArticles;
	}

	public void setFavourites(Set<Article> favourites) {
		this.favouriteArticles = favourites;
	}
	
	
	
}
