package com.esprit.microservice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int id;
	
	private String content;
	
	@Temporal(TemporalType.DATE)
	private Date created_at;
	
	private int user;
	private int post;
	private int annonce;
	
	@Enumerated(EnumType.STRING)
	private role role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public int getAnnonce() {
		return annonce;
	}

	public void setAnnonce(int annonce) {
		this.annonce = annonce;
	}

	public role getRole() {
		return role;
	}

	public void setRole(role role) {
		this.role = role;
	}

	public Comment(String content, Date created_at, int user, int post, int annonce,
			com.esprit.microservice.role role) {
		super();
		this.content = content;
		this.created_at = created_at;
		this.user = user;
		this.post = post;
		this.annonce = annonce;
		this.role = role;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
