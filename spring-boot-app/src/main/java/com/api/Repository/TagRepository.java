package com.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.Models.Tag;
import com.api.Models.User;
@Repository
public interface TagRepository  extends JpaRepository<Tag, Long> {
	List<Tag> findByTagText(String tagText);
}
