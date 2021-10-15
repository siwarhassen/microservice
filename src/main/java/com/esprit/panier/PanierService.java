package com.esprit.panier;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.*;
@Service
public class PanierService {

	
	@Autowired
	private PanierRepository panierrepo;
	
	@Autowired
	private PanierItemRepository panierItemrepo;
	
	
	public Panier addPanierFirstTime(int idproduit, int clientId, int quantity) {
		Panier panier = new Panier();
		PanierItem panierItem = new PanierItem();
		panierItem.setQuantity(quantity);
		
		panierItem.setProduit(idproduit);
		panier.getItems().add(panierItem);
		panier.setClient(clientId);
		panier.setDate(new Date());
		return panierrepo.save(panier);

	}
	
	public Panier getPanierByUser(int clientId) {
		return  panierrepo.findPanierByClient(clientId);
	}
	
	
	public void clearPanier(int clientId) {
		Panier p = panierrepo.findPanierByClient(clientId);
		panierrepo.delete(p);	
	}
	
	
	public Panier removePanierIemFromPanier(Long idPanierItem, int clientId) {
		Panier panier = panierrepo.findPanierByClient(clientId);
		Set<PanierItem> items = panier.getItems();
		PanierItem panierItem = null;
		for(PanierItem item : items) {
			if(item.getId()==idPanierItem) {
				panierItem = item;
			}
		}
		items.remove(panierItem);
		panierItemrepo.delete(panierItem);
	    panier.setItems(items);
	    return panierrepo.save(panier);
	}
	
	
	public PanierItem updatePanierItem(Long idPanierItem, int quantity) {
		PanierItem panierItem = panierItemrepo.findById(idPanierItem).get();
		panierItem.setQuantity(quantity);
		return panierItemrepo.saveAndFlush(panierItem);
		
	}
	
	
	public Panier addToExistingPanier(int idprod,int clientId, int quantity) {

		Panier panier = panierrepo.findPanierByClient(clientId);
		//Produit p = productService.getProductById(id);
		Boolean productDoesExistInPanier = false;
		if (panier != null) {
		    Set<PanierItem> items = panier.getItems();
			for (PanierItem item : items) {
				if (item.getProduit()==idprod) {
					productDoesExistInPanier = true;
					item.setQuantity(item.getQuantity() + quantity);
					panier.setItems(items);
					return panierrepo.saveAndFlush(panier);  
				}
				
			}
		}
		if(!productDoesExistInPanier && (panier != null))
		{
			PanierItem panierItem1 = new PanierItem();
			
			panierItem1.setQuantity(quantity);
			panierItem1.setProduit(idprod);
			panier.getItems().add(panierItem1);
			return panierrepo.saveAndFlush(panier);
		}
		
		return this.addPanierFirstTime(idprod, clientId, quantity);

	}
	
}
