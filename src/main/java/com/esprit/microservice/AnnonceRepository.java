package com.esprit.microservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {

	//@Query("select a from Annonce a where a.titre like :titre")
	//Recherche avancé par titre
	@Query("select a from Annonce a where a.titre like %?1%")
	public List<Annonce> annonceByTitre(@Param("titre")String titre);
	
	@Query("select a from Annonce a where a.user_Id like :user")
	public List<Annonce> annonceByUser(@Param("user")String user);

}
