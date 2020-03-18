package com.example.infrrd.enities;

import java.time.LocalDateTime;

import com.example.infrrd.enums.OperationsType;

public class InventoryAudit {

	private Inventory invenTory;
	private OperationsType operation;
	private LocalDateTime timeStamp;
	public InventoryAudit(Inventory invenTory, OperationsType operation) {
		super();
		this.invenTory = invenTory;
		this.operation = operation;
		this.timeStamp = LocalDateTime.now();
	}
	public Inventory getInvenTory() {
		return invenTory;
	}
	public void setInvenTory(Inventory invenTory) {
		this.invenTory = invenTory;
	}
	public OperationsType getOperation() {
		return operation;
	}
	public void setOperation(OperationsType operation) {
		this.operation = operation;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "InventoryAudit [invenTory=" + invenTory + ", operation=" + operation + "]";
	}
	
}
