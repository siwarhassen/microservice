package tn.esprit.microserviceproduit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value= "api/produit")

public class ProduitRestAPI {
	@Autowired
	ProduitService produitService;


	@PostMapping("/addproduit")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<Produit>  addProduit(@RequestBody Produit produit) {
		 return new ResponseEntity<>( produitService.addProduit(produit), HttpStatus.OK);
	
	 }
	 @DeleteMapping(value = "/{id}")
	 @ResponseStatus(HttpStatus.OK)
	 public String deleteProduit(@PathVariable("id") int id) {
		 produitService.deleteProduit(id);
		  return "deleted";
	}
	 
	 @PutMapping(value = "/{id}")
	 @ResponseStatus(HttpStatus.OK)
	 public ResponseEntity<Produit>  updateProduit(@PathVariable("id") int id, @RequestBody Produit updatedproduit) {
		 return new ResponseEntity<>( produitService.updateProduit(id, updatedproduit), HttpStatus.OK);
	
	 }
	 
	 @GetMapping("/getproduits")
	 @ResponseBody
	 public List<Produit> getProduits() {
	 List<Produit> list = produitService.getAllProduits();
	 return list;
	 }
	 

	 
	 @GetMapping("/getproduits/{title}")
	 @ResponseBody
	 public List<Produit> getProduitsByTitle(@PathVariable("title") String title) {
	 List<Produit> list = produitService.findProduitsByTitle(title);
	 return list;
	 }
	 
	 @GetMapping("/getproduit/{id}")
	 @ResponseBody
	 public Produit getProduitsByTitle(@PathVariable("id") int id) {
	 Produit produit = produitService.getProduit(id);
	 return produit;
	 }
	 
	 
	 

	 
}
