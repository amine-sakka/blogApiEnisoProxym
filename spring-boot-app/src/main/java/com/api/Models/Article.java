package com.api.Models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","usersHowLikeThis"})

public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
	private String slug;
	private String title;
	private String description;
	private String body;
	private Date createdAt;
	private Date updatedAt;
	private boolean favorited;
	private Integer favoritesCount;
	
	
	@ManyToOne()
	@JsonIgnore
	@JsonManagedReference
	private User author ;
	
	@JsonIgnore
	@OneToMany(mappedBy = "article")
	private List<Comment> comments;
	
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "articles", fetch = FetchType.EAGER)
    private Set<Tag> tags;
	
	
	/*@ManyToMany(fetch = FetchType.EAGER ,targetEntity = User.class)
	@JoinTable(name = "Favourties", joinColumns = { @JoinColumn(name = "ARTICLE_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "USER_ID") })

	private Set<User> usersHowLikeThis;*/
	@ManyToMany( mappedBy = "favouriteArticles",targetEntity=User.class,fetch = FetchType.LAZY)
	private Set <User> usersHowLikeThis;
	
	public Article() {
		super();
	}

	public Article(long id, String slug, String title, String description, String body, Date createdAt, Date updatedAt,
			boolean favorited, Integer favoritesCount, User author) {
		super();
		this.id = id;
		this.slug = slug;
		this.title = title;
		this.description = description;
		this.body = body;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.favorited = favorited;
		this.favoritesCount = favoritesCount;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean isFavorited() {
		return favorited;
	}

	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	public Integer getFavoritesCount() {
		return favoritesCount;
	}

	public void setFavoritesCount(Integer favoritesCount) {
		this.favoritesCount = favoritesCount;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<User> getUsersHowLikeThis() {
		return usersHowLikeThis;
	}

	public void setUsersHowLikeThis(Set<User> usersHowLikeThis) {
		this.usersHowLikeThis = usersHowLikeThis;
	}
	
	
	
	
	
}
