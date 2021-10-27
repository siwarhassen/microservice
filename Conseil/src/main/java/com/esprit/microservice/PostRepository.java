package com.esprit.microservice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface PostRepository extends JpaRepository<Post, Integer>{

	@Query("select c from Post c where c.title like :title")
	public List<Post> PostPostByTitle(@Param("title") String n);
	
		
	@Query("select p from Post p where p.user=:user")
	public List<Post> findPostByUser(@Param("user")int user);
	
}
