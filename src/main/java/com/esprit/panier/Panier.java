package com.esprit.panier;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.*;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Panier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	
	@Transient
	private Double totalPrice ; 
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	private Set<PanierItem> items = new HashSet<PanierItem>();
	
	private int client;

	@Transient
	private int itemsNumber;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Double getTotalPrice() {
		Double sum=0.0;
		for(PanierItem item:this.items )
		{
			sum=sum +item.getProduit()*item.getQuantity();
		}
		return sum;
	}

	
	public int getItemsNumber() {
		return this.items.size();
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<PanierItem> getItems() {
		return items;
	}

	public void setItems(Set<PanierItem> items) {
		this.items = items;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public Panier() {
		super();
	}

	public Panier( Double totalPrice, Set<PanierItem> items, int client) {
		super();
	
		this.totalPrice = totalPrice;
		this.items = items;
		this.client = client;
	}

	public Panier(Long id, Double totalPrice, Set<PanierItem> items, int client) {
		super();
		this.id = id;
		
		this.totalPrice = totalPrice;
		this.items = items;
		this.client = client;
	}

	public Panier(Long id,Double totalPrice, Set<PanierItem> items, int client, int itemsNumber) {
		super();
		this.id = id;
		
		this.totalPrice = totalPrice;
		this.items = items;
		this.client = client;
		this.itemsNumber = itemsNumber;
	}

	public Panier( Double totalPrice, Set<PanierItem> items, int client, int itemsNumber) {
		super();
		
		this.totalPrice = totalPrice;
		this.items = items;
		this.client = client;
		this.itemsNumber = itemsNumber;
	}
	
	
}

