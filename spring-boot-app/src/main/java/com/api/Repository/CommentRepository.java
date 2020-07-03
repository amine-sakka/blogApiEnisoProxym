package com.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.Models.Comment;
@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long>{

}
