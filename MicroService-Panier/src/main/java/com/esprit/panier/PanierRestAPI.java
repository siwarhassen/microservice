package com.esprit.panier;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/panier")
public class PanierRestAPI {
	
	@Autowired
	private PanierService panierservice;
	
	
	
	//creation du panier quand l'utilisateur fait la creation du compte
	@PostMapping("/createPanier")
	public Panier createPanier(@RequestParam("clientId") int clientId) {

		return  panierservice.createPanier(clientId);	
	}
	
	
	@GetMapping("/panierofUser/{clientId}")
	public Panier showPanierOfUser(@PathVariable("clientId") int clientId) {
		
		return panierservice.getPanierByUser(clientId);
	}

	
	
	
	@PutMapping("/addItemToPanier/")
	public Panier addItemToPanier(@RequestParam("idprod") int idprod,@RequestParam("idclient") int idclient,@RequestParam("quantity") int quantity) {

	
		return  panierservice.addItemToPanier(idprod, idclient, quantity);
		
	}
	
	
	
	
	@PutMapping("/removePanierItem/{idPanierItem}/{clientId}")
	public Panier removeItem(@PathVariable("idPanierItem") Long idPanierItem, @PathVariable("clientId") int clientId) {
	
		return panierservice.removePanierIemFromPanier(idPanierItem, clientId);
		
	}
	


	
	@PutMapping("/updatePanier")
	public String updatePanierItem(@RequestParam("idPanierItem") Long idPanierItem,
			@RequestParam("quantity") int quantity) {
		
		panierservice.updatePanierItem(idPanierItem, quantity);
		return "panierItem updated";
	}
	
	
	
	
	@PutMapping("/deleteAllItem/{clientId}")
	public Panier deleteAllItem(@PathVariable("clientId") int clientId) {
		
		return panierservice.deleteAllItemPanier(clientId);
		
	}
	
	
	
	
	@DeleteMapping("/clearPanier/{clientId}")
	public String clearPanier(@PathVariable("clientId") int clientId) {
		
		panierservice.clearPanier(clientId);
		return "ok";
	}
	

	

	
	 @GetMapping("/getPanierItemsByDate")
	 public  Set<PanierItem> getProductsbyCurrentDate(@RequestParam("idclient") int idclient,@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date d) {
	   return panierservice.searchPanierItemsByDate(idclient,d);

	 }
	
	
	   @GetMapping("/allPaniers")
	   public List<Panier> getListPanier() {
		   return this.panierservice.findAllPanierForAdmin();
		   
	   }

}
