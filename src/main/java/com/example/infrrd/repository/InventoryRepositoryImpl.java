package com.example.infrrd.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.infrrd.enities.Inventory;
import com.example.infrrd.enities.InventoryAudit;
import com.example.infrrd.enums.OperationsType;
import com.example.infrrd.enums.Statics;
import com.example.infrrd.exceptions.ResourceNotFoundException;

@Component
public class InventoryRepositoryImpl implements InventoryRepository {
	
	private Map<String, Inventory> inventoyStorage = new HashMap<String, Inventory>();
	private List<InventoryAudit> inventoyAudit = new LinkedList<InventoryAudit>();

	@Override
	public String addToInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		Inventory newInvetory = inventoyStorage.get(inventory.getName());
		if( null!= newInvetory ) {
			//If the product already exist in the inventory only add the new quantity 
			//to existing Quantity and update the timestamp
			newInvetory.setQuantity(newInvetory.getQuantity()+inventory.getQuantity());
			newInvetory.setDesCription(inventory.getDesCription());
			inventoyAudit.add(0,new InventoryAudit(inventory, OperationsType.ADD));// Adding audit entry to the first position
			
		}else {
			//Creating a new Entry to the inventory Storage
			newInvetory = new Inventory(inventory.getName(), inventory.getQuantity(), inventory.getDesCription());
			inventoyStorage.put(inventory.getName(),newInvetory);
			inventoyAudit.add(0,new InventoryAudit(newInvetory, OperationsType.ADD));
		}
		
		return Statics.PROCESSING_SUCCESS;
}

	@Override
	public List<Inventory> getAllInventory() {
		return inventoyStorage.values().parallelStream().collect(Collectors.toList());
	}

	@Override
	public List<InventoryAudit> getAllInventoryAudit() {
		// TODO Auto-generated method stub
		return inventoyAudit;
	}

	@Override
	public List<InventoryAudit> getAllInventoryAudit(LocalDateTime to, LocalDateTime from) {
		// TODO Auto-generated method stub
		return inventoyAudit.parallelStream().
				filter(audit-> to.isAfter(audit.getTimeStamp()) 
							&& from.isBefore(audit.getTimeStamp())).collect(Collectors.toList());
	}

	@Override
	public String remove(String inventoryName, int numberOfIteams) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		if(null != inventoyStorage.get(inventoryName)) {
			if(inventoyStorage.get(inventoryName).getQuantity()>numberOfIteams && numberOfIteams != 0) {
				inventoyStorage.get(inventoryName).setQuantity(inventoyStorage.get(inventoryName).getQuantity() - numberOfIteams);
				inventoyAudit.add(0,new InventoryAudit(inventoyStorage.get(inventoryName), OperationsType.REMOVE));
			}else {
				inventoyStorage.remove(inventoryName);
				inventoyAudit.add(0,new InventoryAudit(inventoyStorage.get(inventoryName), OperationsType.REMOVE));
			}
			return Statics.ITEAMS_REMOVED;
		}else {
			throw new ResourceNotFoundException(inventoryName+" does not exisits in inventory list.");
		}
	}

	@Override
	public Inventory getInventoryDetails(String inventoryName) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		if(null != inventoyStorage.get(inventoryName)) {
			return inventoyStorage.get(inventoryName);
		}
		else {
			throw new ResourceNotFoundException(inventoryName+" does not exisits in inventory list.");
		}
	}

}
