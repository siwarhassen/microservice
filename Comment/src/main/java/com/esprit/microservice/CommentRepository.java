package com.esprit.microservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	@Query("select c from Comment c where c.role=:role")
	public List<Comment> findCommentByRole(@Param("role") role role);
	
	
	@Query("select c from Comment c where c.user=:user")
	public List<Comment> findCommentByUser(@Param("user")int user);
}
