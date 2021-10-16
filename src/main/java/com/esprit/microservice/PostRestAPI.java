package com.esprit.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	 public List<Post> getPosts() {
	 List<Post> list = postService.retrieveAllPosts();
	 return list;
	 }
	 
	 @GetMapping("/retrieve-my-posts/{user}")
	 @ResponseBody
	 public List<Post> getMyPosts(@PathVariable("user") int user) {
	 List<Post> list = postService.retrieveMyPosts(user);
	 return list;
	 }
	 
	 @GetMapping("/retrieve-posts-title/{title}")
	 @ResponseBody
	 public List<Post> getPostsByTitle(@PathVariable("title") String title) {
	 List<Post> list = postService.findPostsByTitle(title);
	 return list;
	 }
	 
	 @GetMapping("/retrieve-post/{id}")
	 @ResponseBody
	 public Post getPostsByTitle(@PathVariable("id") int id) {
	 Post post = postService.retrievePost(id);
	 return post;
	 }
	 
	 @PostMapping("/add-post/{user}")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<Post>  addPost(@RequestBody Post p, @PathVariable("user") int user) {
		 return new ResponseEntity<>( postService.addPost(p,user), HttpStatus.OK);
	
	 }
	 
	 @PutMapping(value = "/{id}/{user}", produces= MediaType.APPLICATION_JSON_VALUE)
	 @ResponseStatus(HttpStatus.OK)
	 public ResponseEntity<Post>  updatePost(@PathVariable("id") int id,@PathVariable("user") int user, @RequestBody Post p) {
		 return new ResponseEntity<>( postService.updatePost(id,user,p), HttpStatus.OK);
	
	 }
	 
	 @DeleteMapping(value = "/{id}/{user}", produces= MediaType.APPLICATION_JSON_VALUE)
	 @ResponseStatus(HttpStatus.OK)
	 public String deletePost(@PathVariable("id") int id,@PathVariable("user") int user) {
		  postService.deletePost(id,user);
		  return "Post deleted with success";
	}
	 
	
	 
}
