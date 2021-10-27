package com.esprit.panier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface PanierRepository extends JpaRepository<Panier, Long>{

	

	 @Transactional
	@Query("select p from Panier p where p.client=:clientId")
	public Panier findPanierByClient(@Param("clientId")int clientId);
	
}
