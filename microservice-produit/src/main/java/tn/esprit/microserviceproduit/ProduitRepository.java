package tn.esprit.microserviceproduit;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ProduitRepository extends JpaRepository<Produit, Integer>{

	@Query("select p from Produit p where p.ID=:ID")
	public List<Produit> ProduitByTitle(@Param("ID") Int ID);
}


