package com.hibernate.hospital.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "address")
	@SequenceGenerator(name = "address", initialValue = 1, allocationSize = 1, sequenceName = "address_sequence")
	private int addressId;
	private int adressDoorNo;
	private String addressStreet;
	private String addressCity;
	private String addressState;
	private int addressPincode;

	@OneToOne(mappedBy = "address")
	private Branch branch;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getAdressDoorNo() {
		return adressDoorNo;
	}

	public void setAdressDoorNo(int adressDoorNo) {
		this.adressDoorNo = adressDoorNo;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public int getAddressPincode() {
		return addressPincode;
	}

	public void setAddressPincode(int addressPincode) {
		this.addressPincode = addressPincode;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
}
