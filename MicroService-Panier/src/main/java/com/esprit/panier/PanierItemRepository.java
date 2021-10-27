package com.esprit.panier;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PanierItemRepository extends JpaRepository<PanierItem, Long>{

	
	
	@Query("select pi from PanierItem pi where pi.date <= :datesearch and pi.id=:id")
  PanierItem findPanierItemsByDate(  @Param("datesearch") Date datesearch,@Param("id") Long id);
	
	
	
	/*@Query("select pi from PanierItem pi where pi.produit.titre like %?1% and pi.id=:id")
	  PanierItem findPanierItemsByNameOfProduct(  @Param("nameProd") String nameProd,@Param("id") Long id);*/
}
