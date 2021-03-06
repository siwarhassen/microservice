package com.esprit.panier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class PanierItem  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	
	private int quantity;
	
	//@ManyToOne(fetch=FetchType.EAGER)
	private int produit;
	

	
	
	
	public PanierItem(int quantity, int produit) {
		super();
		this.quantity = quantity;
		this.produit = produit;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PanierItem other = (PanierItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProduit() {
		return produit;
	}

	public void setProduit(int produit) {
		this.produit = produit;
	}

	public PanierItem(Long id, int quantity, int produit) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.produit = produit;
	}

	public PanierItem() {
		super();
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public PanierItem(Long id, Date date, int quantity, int produit) {
		super();
		this.id = id;
		this.date = date;
		this.quantity = quantity;
		this.produit = produit;
	}



	public PanierItem(Date date, int quantity, int produit) {
		super();
		this.date = date;
		this.quantity = quantity;
		this.produit = produit;
	}



	



	
	

}
