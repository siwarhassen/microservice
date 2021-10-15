package com.esprit.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value= "api/posts")
public class PostRestAPI {
	
	@Autowired
	PostService postService;

	private String title ="Hello Post";
	
	@RequestMapping("/hello")
	public String sayHello() {
		System.out.println(title);
		return title;
	}
	
	 @GetMapping("/retrieve-all-posts")
	 @ResponseBody
	 public List<Post> getUsers() {
	 List<Post> list = postService.retrieveAllPosts();
	 return list;
	 }
	 
	 @PostMapping("/add-user")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<Post>  addUser(@RequestBody Post p) {
		 return new ResponseEntity<>( postService.addPost(p), HttpStatus.OK);
	
	 }
	 
	 @PutMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	 @ResponseStatus(HttpStatus.OK)
	 public ResponseEntity<Post>  updateUser(@PathVariable("id") int id, @RequestBody Post p) {
		 return new ResponseEntity<>( postService.updatePost(id,p), HttpStatus.OK);
	
	 }
	 
	 @DeleteMapping(value = "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	 @ResponseStatus(HttpStatus.OK)
	 public String deletePost(@PathVariable("id") int id) {
		  postService.deletePost(id);
		  return "Post deleted with success";
	}
	 
	
	 
}
