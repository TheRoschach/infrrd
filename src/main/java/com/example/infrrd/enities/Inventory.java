package com.example.infrrd.enities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Inventory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6594786262759065700L;
	private String name;
	private String desCription;
	private int quantity;
	public Inventory(String name,int quantity, String desCription) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.desCription = desCription;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesCription() {
		return desCription;
	}
	public void setDesCription(String desCription) {
		this.desCription = desCription;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Inventory [name=" + name + ", desCription=" + desCription + ", quantity=" + quantity +  "]";
	}
	

}
