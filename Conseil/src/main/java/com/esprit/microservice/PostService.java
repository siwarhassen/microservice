package com.esprit.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	
	public List<Post> retrieveMyPosts(int user){
		return (List<Post>) repository.findPostByUser(user);
	}
	
	public List<Post> findPostsByTitle(String title){
		return (List<Post>) repository.PostPostByTitle(title);
	}
	
	public Post retrievePost(int id){
		return repository.findById(id).get();
	}
	
	public Post addPost(Post p, int user) {
		p.setCreated_at(new Date());
		p.setUser(user);
		repository.save(p);
		return p;
	}
	
	public String deletePost(int id, int user) {
		Post post = repository.findById(id).get();
		if (repository.findById(id).isPresent() && post.getUser()==user) {
			repository.deleteById(id);
			return "Post deleted";
		}else
			return "Post not deleted";
	}
	
	public Post updatePost(int id, int user, Post newPost) {
		Post post = repository.findById(id).get();
		if (repository.findById(id).isPresent() && post.getUser()==user) {
			post.setTitle(newPost.getTitle());
			post.setContent(newPost.getContent());
			post.setUser(user);
			post.setCreated_at(new Date());
			return  repository.save(post);
		}else 
			return null;
		
	}
}

	
