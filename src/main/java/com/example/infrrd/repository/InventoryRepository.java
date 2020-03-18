package com.example.infrrd.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.infrrd.enities.Inventory;
import com.example.infrrd.enities.InventoryAudit;
import com.example.infrrd.exceptions.ResourceNotFoundException;

public interface InventoryRepository {

	String addToInventory(Inventory inventory);

	List<Inventory> getAllInventory();

	List<InventoryAudit> getAllInventoryAudit();

	List<InventoryAudit> getAllInventoryAudit(LocalDateTime from, LocalDateTime to);

	String remove(String inventoryName, int numberOfIteams) throws ResourceNotFoundException;

	Inventory getInventoryDetails(String inventoryName) throws ResourceNotFoundException;

}
