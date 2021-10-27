package com.esprit.panier;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
	
	
	
	
	public Panier createPanier(int clientId)
	{
		Panier panier = new Panier();
		panier.setClient(clientId);
		
		return panierrepo.save(panier);
	}
	
	
	public Panier getPanierByUser(int clientId) {
		return  panierrepo.findPanierByClient(clientId);
	}
	
		
	
	public Panier addItemToPanier(int idprod,int clientId, int quantity) {

		Panier panier = panierrepo.findPanierByClient(clientId);
		//Produit p = productService.getProductById(id);
		Boolean productDoesExistInPanier = false;
	
		    Set<PanierItem> items = panier.getItems();
			for (PanierItem item : items) {
				if (item.getProduit()==idprod) {
					productDoesExistInPanier = true;
					item.setQuantity(item.getQuantity() + quantity);
					panier.setItems(items);
					return panierrepo.saveAndFlush(panier);  
				}
				
			}
		
		if(!productDoesExistInPanier )
		{
			PanierItem panierItem1 = new PanierItem();
			panierItem1.setDate(new Date());
			panierItem1.setQuantity(quantity);
			panierItem1.setProduit(idprod);
			panier.getItems().add(panierItem1);
			
		}
		return panierrepo.saveAndFlush(panier);
	

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
	
	

	public Panier deleteAllItemPanier(int clientId)
	{
		Panier p = panierrepo.findPanierByClient(clientId);
		Set<PanierItem> items = p.getItems();
		
		for(PanierItem item : items) {
			items.remove(item);
			panierItemrepo.delete(item);
			
		}
		  p.setItems(items);
		    return panierrepo.save(p);
	}
	
	
	
	public void clearPanier(int clientId) {
		Panier p = panierrepo.findPanierByClient(clientId);
		panierrepo.delete(p);	
	}
	
	

	
	public Set<PanierItem> searchPanierItemsByDate(int clientId,Date d)
	{
		Panier p = panierrepo.findPanierByClient(clientId);
		Set<PanierItem> items = p.getItems();
		Set<PanierItem> itemsbyDate= new HashSet<PanierItem>();
		for(PanierItem item : items) {
			if(panierItemrepo.findPanierItemsByDate(d, item.getId())!=null)
			{
				itemsbyDate.add(item);
			}
			
		}
		return  itemsbyDate;
	
	}

	
	public List<Panier> findAllPanierForAdmin()
	{
		return panierrepo.findAll();
	}
	
}
