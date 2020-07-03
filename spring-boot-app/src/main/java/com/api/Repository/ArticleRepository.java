package com.api.Repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.Models.Article;
import com.api.Models.Tag;
import com.api.Models.User;
@Repository
public interface ArticleRepository  extends JpaRepository<Article, Long> {

	
	List<Article> findByTitle(String title);
	List<Article> findBySlug(String slug);
	List< Article> findByAuthor(User author);
	List<Article> findByTags(Tag tag);
	
	//List<Article> findByTagsPagine(Tag tag,PageRequest pageable );
	@Query(value="SELECT * FROM article WHERE author_id = ?1", nativeQuery = true)
	public List<Article> findByAuthorId(@Param("author_id") Long authorId,PageRequest pageable);
	 
	@Query(value="SELECT * FROM article arc, article_tag arct WHERE arc.id=arct.article_id AND arct.tag_id=?1", nativeQuery = true)
	public List<Article> findByTagId(@Param("tag_id") Long tagId,PageRequest pageable);
	
	@Query(value="SELECT * FROM article arc, article_tag arct WHERE arc.id=arct.article_id AND arct.tag_id=?1 AND arc.author_id=?2", nativeQuery = true)
	public List<Article> findByTagIdAndAuthorId(@Param("tag_id") Long tag_id,@Param("author_id") Long authorId,PageRequest pageable);


	
}
