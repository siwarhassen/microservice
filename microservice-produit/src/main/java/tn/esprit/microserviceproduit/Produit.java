package tn.esprit.microserviceproduit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Produit {
	
@Id
	@GeneratedValue
	private Long id;
	

	@Temporal(TemporalType.DATE)
	private Date created_at;
	

	private String title,type,description,image;
	
	@Transient
	private Double price ; 
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}





	private int  quantity;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", created_at=" + created_at + ", title=" + title + ", type=" + type
				+ ", description=" + description + ", price=" + price + ", quantity=" + quantity + "]";
	}

	public Produit(Long id, Date created_at, String title, String type, String description, Double price,
			int quantity) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.title = title;
		this.type = type;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	

	public Produit(Long id, Date created_at, String title, String description, Double price, int quantity) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.title = title;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	


	public Produit(Long id, Date created_at, String title, String type, String description, int quantity) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.title = title;
		this.type = type;
		this.description = description;
		this.quantity = quantity;
	}

	
	public Produit(Long id, Date created_at, String title, String type, String description, Double price , String image) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.title = title;
		this.type = type;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	
	
	
	

	public Produit() {
		super();
		
	}


	
	

	
	
	
}
