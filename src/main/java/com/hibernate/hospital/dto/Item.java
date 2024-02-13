package com.hibernate.hospital.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "item")
	@SequenceGenerator(name = "item", initialValue = 1, allocationSize = 1, sequenceName = "item_sequence")
	private int itemId;
	private String itemName;
	private double itemPrice;
	@CreationTimestamp
	private LocalDateTime packingDate;
	@UpdateTimestamp
	private LocalDateTime expiredDate;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "medOrder_id"))
	private List<MedOrders> medOrders;
	

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public LocalDateTime getPackingDate() {
		return packingDate;
	}

	public void setPackingDate(LocalDateTime packingDate) {
		this.packingDate = packingDate;
	}

	public LocalDateTime getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDateTime expiredDate) {
		this.expiredDate = expiredDate;
	}

	public List<MedOrders> getMedOrders() {
		return medOrders;
	}

	public void setMedOrders(List<MedOrders> medOrders) {
		this.medOrders = medOrders;
	}
}
