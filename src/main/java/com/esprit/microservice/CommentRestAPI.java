package com.esprit.microservice;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "api/comments")
public class CommentRestAPI {

	@Autowired
	CommentService commentService;
	
	 @GetMapping("/retrieve-all-comments")
	 @ResponseBody
	 public List<Comment> getComments() {
	 List<Comment> list = commentService.retrieveAllComments();
	 return list;
	 }
	 
	 @GetMapping("/retrieve-comments-user/{user}")
	 @ResponseBody
	 public List<Comment> getMyComments(@PathVariable("user") int user) {
	 List<Comment> list = commentService.retrieveCommentsUser(user);
	 return list;
	 }	 
	  
	 @GetMapping("/retrieve-comments-posts")
	 @ResponseBody
	 public List<Comment> getMyComments(@RequestParam role role) {
	 List<Comment> list = commentService.retrievePostComments(role);
	 return list;
	 }
	 
	 @GetMapping("/retrieve-comment/{id}")
	 @ResponseBody
	 public Comment getComment(@PathVariable("id") int id) {
	 Comment c = commentService.retrieveComment(id);
	 return c;
	 }
	
	 @PostMapping("/add-comment/{user}/{id}")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<Comment>  addComment(@RequestBody Comment c, @PathVariable("user") int user, @PathVariable("id") int id, @RequestParam role role) {
		 return new ResponseEntity<>( commentService.addComment(c,user,id,role), HttpStatus.OK);
	
	 }
	 
	 @PutMapping(value = "/{id}/{user}", produces= MediaType.APPLICATION_JSON_VALUE)
	 @ResponseStatus(HttpStatus.OK)
	 public ResponseEntity<Comment>  updateComment(@PathVariable("id") int id,@PathVariable("user") int user, @RequestBody Comment p, @RequestParam role role) { 
		 if (commentService.updateComment(id,user,p,role)!=null) {
			 return new ResponseEntity<>( commentService.updateComment(id,user,p,role), HttpStatus.OK);
		 }else
			 return null;
	 }
	 
	 @DeleteMapping(value = "/{id}/{user}", produces= MediaType.APPLICATION_JSON_VALUE)
	 @ResponseStatus(HttpStatus.OK)
	 public String deleteComment(@PathVariable("id") int id,@PathVariable("user") int user) {
		 if (commentService.deleteComment(id,user).equals("True")) {
			 return "Comment deleted with success";
		 }else
			 return "Comment not deleted";	  
	}
}
