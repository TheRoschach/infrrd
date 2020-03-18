package com.example.infrrd.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.infrrd.enities.Inventory;
import com.example.infrrd.enities.InventoryAudit;
import com.example.infrrd.exceptions.ResourceNotFoundException;
import com.example.infrrd.service.InventoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	 @ApiOperation(value = "Get all Inventory details")
	 @GetMapping("/inventory")
	 public List<Inventory> getAllinventory() {
	  return inventoryService.getAllInventory();
	 }
	 
	 @ApiOperation(value = "Get Inventory details of a perticular iteam")
	 @GetMapping("/inventory/{inventoryName}")
	 public ResponseEntity<Inventory> getInventoryDetails(@ApiParam(value = "Inventory iteam name to be viewed", required = true)
	 @PathVariable String inventoryName) throws ResourceNotFoundException {
	  return ResponseEntity.ok(inventoryService.getInventoryDetails(inventoryName));
	 }
	 
	 @ApiOperation(value = "Get all Inventory Operations")
	 @GetMapping("/inventoryAuditTrail/")
	 public List<InventoryAudit> getAlloperations() {
	  return inventoryService.getAlloperations();
	 }
	 
	 @ApiOperation(value = "Get all Inventory Operations betweenc certain time frame")
	 @GetMapping("/inventoryAuditTrail")
	 public List<InventoryAudit> getAlloperations(
			 @ApiParam(value = "Search time frame to (Upper limit)", required = true)
			 @RequestParam(name = "to")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
			 @ApiParam(value = "Search time frame From (Lower limit)", required = true)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			 @RequestParam LocalDateTime from
			) {
		 		return inventoryService.getAlloperations(to,from);
	 }
	 
	 	@ApiOperation(value = "Add an iteam Inventory")
	 	@PostMapping("/inventory")
	 	public ResponseEntity<String> addInventory (
	     @ApiParam(value = "Inventory object to be added ,if already exisits quantity will be added to the existing count", required = true)
	     @RequestBody Inventory inventory) throws Exception {
		 System.out.println("Inventory Input - " + inventory);
	        return ResponseEntity.ok(inventoryService.save(inventory));
	    }

	 	@ApiOperation(value = "Remove an iteam Inventory")
	 	@DeleteMapping("/inventory/{inventoryName}")
	 	public ResponseEntity<String> removeFromInventory(
	     @ApiParam(value = "Inventory object to be removed", required = true) @PathVariable String inventoryName,
	     @ApiParam(value = "Number of Inventory object to be removed and if left empty all the iteams will be removed of that iteam", required = false) 
	     @RequestParam("numberofIteams") Optional<Integer>  numberOfIteams) throws ResourceNotFoundException,Exception{
		 System.out.println("Inventory removing - " + inventoryName +" numberOfIteams - "+numberOfIteams);
	        return ResponseEntity.ok(inventoryService.remove(inventoryName,Integer.valueOf(numberOfIteams.orElse(0))));
	    }

	
}
