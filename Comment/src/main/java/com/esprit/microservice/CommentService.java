package com.esprit.microservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository repository;
	
	public List<Comment> retrieveAllComments(){
		return (List<Comment>) repository.findAll();
	}
	
	public List<Comment> retrievePostComments(role role){
		return (List<Comment>) repository.findCommentByRole(role);
	}
	
	public List<Comment> retrieveCommentsUser(int user){
		return (List<Comment>) repository.findCommentByUser(user);
	}
	
	public Comment retrieveComment(int id){
		return repository.findById(id).get();
	}
	
	public Comment addComment(Comment c, int user, int id, role role) {
		if (role.equals(role.ANNONCE) ) {
			c.setCreated_at(new Date());
			c.setUser(user);
			c.setAnnonce(id);
			c.setRole(role);
		} else if (role.equals(role.POST)) {
			c.setCreated_at(new Date());
			c.setUser(user);
			c.setPost(id);
			c.setRole(role);
		}
		repository.save(c);
		return c;
	}
	
	public String deleteComment(int id, int user) {
		Comment comment = repository.findById(id).get();
		if(comment.getUser()!=user) {
			return "False";
			}else {
				if (repository.findById(id).isPresent()) {
					repository.deleteById(id);
					return "True";
				}else
					return "False";
				}
	}
	
	public Comment updateComment(int id, int user, Comment newComment, role role) {
		Comment comment = repository.findById(id).get();
		if (repository.findById(id).isPresent() && comment.getUser()==user) {
			if (role.equals(role.ANNONCE) ) {
				comment.setContent(newComment.getContent());
				comment.setUser(user);
				comment.setCreated_at(new Date());
				comment.setAnnonce(id);
				comment.setRole(role);
			} else if (role.equals(role.POST)) {
				comment.setContent(newComment.getContent());
				comment.setUser(user);
				comment.setCreated_at(new Date());
				comment.setPost(id);
				comment.setRole(role);
				
			}
			return  repository.save(comment);
		}else 
			return null;
		
	}

}
