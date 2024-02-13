package com.hibernate.hospital.dto;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class MedOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "medOrder")
	@SequenceGenerator(name = "medOrder", initialValue = 1, allocationSize = 1, sequenceName = "medOrder_sequence")
	private int medOrderId;
	private int quantity;
	private String invoiceNumber;
	private String paymentMode;

	@ManyToOne
	@JoinColumn(name = "encounter_id")
	private Encounter encounter;

	@ManyToMany(mappedBy = "medOrders")
	private List<Item> items;

	public int getMedOrderId() {
		return medOrderId;
	}

	public void setMedOrderId(int medOrderId) {
		this.medOrderId = medOrderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
