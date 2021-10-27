package tn.esprit.microserviceproduit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProduitService {
	
	@Autowired
	private ProduitRepository repository;
	
	public Produit addProduit(Produit produit) {
		produit.setCreated_at(new Date());
		repository.save(produit);
		return produit;
	}
	
	public String deleteProduit(Integer id) {
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
			return "done";
		}else
			return "error";
	}
	
	
	public List<Produit> getAllProduits(){
		return (List<Produit>) repository.findAll();
	}
	
	
	public List<Produit> findProduitsByTitle(String title){
		return (List<Produit>) repository.ProduitByTitle(title);
	}
	
	public Produit getProduit(Integer id){
		return repository.findById(id).get();
	}
	
	
	public Produit updateProduit(Integer id, Produit updatedproduit) {
		if (repository.findById(id).isPresent()) {
			Produit produit = repository.findById(id).get();
			produit.setTitle(updatedproduit.getTitle());
			produit.setPrice(updatedproduit.getPrice());
			produit.setQuantity(updatedproduit.getQuantity());
			produit.setDescription(updatedproduit.getDescription());
			produit.setType(updatedproduit.getType());
			produit.setCreated_at(new Date());
			produit.setImage("https://ad962edbae8ba7b03b7f-d10007df79b5b7a4e475a291e50a08cf.ssl.cf3.rackcdn.com/ouvrir-une-animalerie/ouvrir-une-animalerie.jpg");
			return  repository.save(produit);
		}else 
			return null;
		
	}
	
	
}
