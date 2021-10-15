package com.esprit.panier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/panier")
public class PanierRestAPI {
	
	@Autowired
	private PanierService panierservice;
	
	
	
	@PostMapping("/addToPanier/")
	public Panier addToPanier(@RequestParam("idprod") int idprod,@RequestParam("idclient") int idclient,@RequestParam("quantity") int quantity) {

	
		return  panierservice.addPanierFirstTime(idprod, idclient, quantity);
		
	}
	
	@GetMapping("/panierofUser/{clientId}")
	public Panier showPanierOfUser(@PathVariable("clientId") int clientId) {
		
		return panierservice.getPanierByUser(clientId);
	}

	
	@DeleteMapping("/clearPanier/{clientId}")
	public String clearPanier(@PathVariable("clientId") int clientId) {
		
		panierservice.clearPanier(clientId);
		return "ok";
	}
	
	
	@GetMapping("/removePanierItem/{idPanierItem}/{clientId}")
	public Panier removeItem(@PathVariable("idPanierItem") Long idPanierItem, @PathVariable("clientId") int clientId) {
	
		return panierservice.removePanierIemFromPanier(idPanierItem, clientId);
		
	}
	
	
	@PostMapping("/updatePanier")
	public String updatePanierItem(@RequestParam("idPanierItem") Long idPanierItem,
			@RequestParam("quantity") int quantity) {
		
		panierservice.updatePanierItem(idPanierItem, quantity);
		return "panierItem updated";
	}
	
	
	
	
	

}
