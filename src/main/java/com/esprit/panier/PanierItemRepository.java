package com.esprit.panier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PanierItemRepository extends JpaRepository<PanierItem, Long>{

}
