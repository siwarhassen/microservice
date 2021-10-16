package com.esprit.microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnonceService {
@Autowired
AnnonceRepository annonceRepository;

//Récupérer la liste de toutes les annonces
public List<Annonce> annonces_list(){
	return this.annonceRepository.findAll();	
}
//Supprimé annonce
public String deleteAnnonce(int id,String user_Id) {
	Optional<Annonce> singleAnnonce= this.annonceRepository.findById(id);
    if(this.annonceRepository.findById(id).isPresent() && singleAnnonce.get().getUser_Id()==user_Id) {
    	this.annonceRepository.deleteById(id);
    	return "Annonce supprimé avec Succés";
    }
    else {
    	return "Vous nous pouvez supprimer cet annonces";
    }
}
//Ajouter Annonce
 public Annonce addAnnonce(Annonce annonce) {
	 return this.annonceRepository.save(annonce);
 }
 //Modifier Annonce
 public String updateAnnonce(int id ,String user_Id,Annonce annonce) {
   Optional<Annonce> annonceData=this.annonceRepository.findById(id);
    if(annonceData.isPresent() && annonceData.get().getUser_Id()==user_Id) {
    	Annonce existingAnnonce=annonceData.get();
    	if(annonce.getContent()!=null) {
    	existingAnnonce.setContent(annonce.getContent());
    	}
    	if(annonce.getTitre()!=null) {
       existingAnnonce.setTitre(annonce.getTitre());
        	}
    	if(annonce.getDescription()!=null) {
        existingAnnonce.setDecription(annonce.getDescription());
        	}
    	
    	this.annonceRepository.save(existingAnnonce);
    	return "Annonce modifié avec succées";
    	
    }
    else {
    	return "Soit cette annonce n'existe pas soit vous n'avez pas droit de la modifié";
    }
 }
 //Afficher Single Annonce
 public Annonce getSigleAnnonce(int id) {
	 return this.annonceRepository.findById(id).get();
 }
 //Rercherche annonce par titre
 public List<Annonce> searchannonce(String titre){
	 return this.annonceRepository.annonceByTitre(titre);
 }
 //Afficher annonce par utilisateur
 public List<Annonce> annonceUser(String user_Id){
	 return this.annonceRepository.annonceByUser(user_Id);
 }
}
