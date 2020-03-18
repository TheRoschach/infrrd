package com.example.infrrd.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.infrrd.enities.Inventory;
import com.example.infrrd.enities.InventoryAudit;
import com.example.infrrd.exceptions.ResourceNotFoundException;
import com.example.infrrd.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private InventoryRepository repos;
	
	public List<Inventory> getAllInventory() {
		
		return repos.getAllInventory();
	}

	@Override
	public String save(Inventory inventory) {
		// TODO Auto-generated method stub
		return repos.addToInventory(inventory);
	}

	@Override
	public List<InventoryAudit> getAlloperations() {
		
		return repos.getAllInventoryAudit();
	}

	@Override
	public List<InventoryAudit> getAlloperations(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return repos.getAllInventoryAudit(from,to);
	}

	@Override
	public String remove(String inventoryName, int numberOfIteams) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return repos.remove(inventoryName,numberOfIteams);
	}

	@Override
	public Inventory getInventoryDetails(String inventoryName) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		 return repos.getInventoryDetails(inventoryName);
	}

}
