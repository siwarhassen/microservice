package tn.esprit.microserviceproduit;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.microserviceproduit.Produit;


public interface ProduitRepository extends JpaRepository<Produit, Integer>{

	@Query("select c from Produit c where c.title like :title")
	public List<Produit> ProduitByTitle(@Param("title") String title);
}


