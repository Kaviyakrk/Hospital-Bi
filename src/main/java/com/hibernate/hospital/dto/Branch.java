package com.hibernate.hospital.dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "branch")
	@SequenceGenerator(name = "branch", initialValue = 1, allocationSize = 1, sequenceName = "branch_sequence")
	private int branchId;
	private String branchName;
	private String branchEmail;
	private String branchCity;
	private long branchContactNo;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy="branch",cascade = CascadeType.ALL)
	private List<Encounter> encounters;

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchEmail() {
		return branchEmail;
	}

	public void setBranchEmail(String branchEmail) {
		this.branchEmail = branchEmail;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public long getBranchContactNo() {
		return branchContactNo;
	}

	public void setBranchContactNo(long branchContactNo) {
		this.branchContactNo = branchContactNo;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
