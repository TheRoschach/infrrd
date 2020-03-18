package com.example.infrrd.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.infrrd.enities.Inventory;
import com.example.infrrd.enities.InventoryAudit;
import com.example.infrrd.exceptions.ResourceNotFoundException;

public interface InventoryService {

	public List<Inventory> getAllInventory();
	public String save(Inventory inventory) throws Exception;
	public List<InventoryAudit> getAlloperations();
	public List<InventoryAudit> getAlloperations(LocalDateTime from, LocalDateTime to);
	public String remove(String inventoryName, int numberOfIteams) throws ResourceNotFoundException;
	public Inventory getInventoryDetails(String inventoryName) throws ResourceNotFoundException;
}
