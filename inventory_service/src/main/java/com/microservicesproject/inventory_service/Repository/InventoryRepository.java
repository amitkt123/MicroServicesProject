package com.microservicesproject.inventory_service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicesproject.inventory_service.Model.Inventory;
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	public Optional<Inventory> findByItemCode(String itemCode);

}
