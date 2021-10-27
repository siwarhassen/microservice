package com.esprit.microservice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="annonces")
public class Annonce implements Serializable {
	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	
@Column(name="titre")
 private String titre;
 
@Column(name="description")
 private String description;
 
@Column(name="content")
 private String content;
@Column(name="user")
 private String user_Id;
@Basic(optional = false)
@CreationTimestamp
@Column(name = "creation_date")
@Temporal(TemporalType.TIMESTAMP)
 private Date dateCreation = new Date();
@Basic(optional = false)
@CreationTimestamp
@Column(name = "update_date")
@Temporal(TemporalType.TIMESTAMP)
 private Date dateUpdate = new Date();

//Constructor



public Annonce()  {
	super();
}

public Annonce(String titre, String description, String content, String user_Id) {
	super();
	this.titre = titre;
	this.description = description;
	this.content = content;
	this.user_Id = user_Id;
	
}
public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}
public String getDescription() {
	return description;
}

public void setDecription(String description) {
	this.description = description;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}

public String getUser_Id() {
	return user_Id;
}
public void setUser_Id(String user_Id) {
	this.user_Id = user_Id;
}
public Date getDateCreation() {
	return dateCreation;
}
public Date getDateUpdate() {
	return dateUpdate;
}


}
