package com.esprit.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public List<Post> retrieveAllPosts(){
		return (List<Post>) repository.findAll();
	}
	
	public Post addPost(Post p) {
		p.setCreated_at(new Date());
		repository.save(p);
		return p;
	}
	
	public String deletePost(int id) {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return "Post deleted";
		}else
			return "Post not deleted";
	}
	
	public Post updatePost(int id, Post newPost) {
		if (repository.findById(id).isPresent()) {
			Post post = repository.findById(id).get();
			post.setTitle(newPost.getTitle());
			post.setContent(newPost.getContent());
			post.setCreated_at(new Date());
			return  repository.save(post);
		}else 
			return null;
		
	}
}

	
