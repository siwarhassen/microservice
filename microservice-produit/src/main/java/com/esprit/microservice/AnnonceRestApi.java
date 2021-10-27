package com.esprit.microservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annonce")
public class AnnonceRestApi {
   @Autowired 
   AnnonceService annonceServices;
   @GetMapping("/list")
   public List<Annonce> getListAnnonce() {
	   return this.annonceServices.annonces_list();
	   
   }
	//Delete annonce by id
	@DeleteMapping("/delete/{id}/{user_Id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteAnnonce(@PathVariable("id")int id,@PathVariable("user_Id")String user_Id){
	     return new ResponseEntity<>(this.annonceServices.deleteAnnonce(id, user_Id),HttpStatus.OK);	
	}
	//Create new annonce
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Annonce>createAnnonce(@RequestBody Annonce annonce){
		return new ResponseEntity<>(this.annonceServices.addAnnonce(annonce),HttpStatus.CREATED);
	}
	//Update Annonce
	@PutMapping("/update/{id}/{user_Id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String>updateAnnonce(@PathVariable("id")int id,@PathVariable("user_Id")String user_Id,@RequestBody Annonce annonce){
		return new ResponseEntity<>(this.annonceServices.updateAnnonce(id,user_Id,annonce),HttpStatus.OK);
	}
   //Afficher annonce par id
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Annonce> getSinlgleAnnonce(@PathVariable("id")int id){
		return new ResponseEntity<>(this.annonceServices.getSigleAnnonce(id),HttpStatus.OK);
	}
	//Recherche Avancé par titre
	@GetMapping("/search/{titre}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Annonce>> searchAnnonce(@PathVariable("titre")String titre){
		return new ResponseEntity<>(this.annonceServices.searchannonce(titre),HttpStatus.OK);
	}
    //Afficher annonce par user
	@GetMapping("/user_Annonce/{user_Id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Annonce>> annonceByUser(@PathVariable("user_Id")String user_Id){
		return new ResponseEntity<>(this.annonceServices.annonceUser(user_Id),HttpStatus.OK);
	}

}
