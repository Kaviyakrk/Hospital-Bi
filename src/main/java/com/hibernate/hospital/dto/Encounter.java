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
import javax.persistence.SequenceGenerator;

@Entity
public class Encounter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "encounter")
	@SequenceGenerator(name = "encounter", initialValue = 1, allocationSize = 1, sequenceName = "encounter_sequence")
	private int encounterId;
	private String encounterType;
	private int bedNumber;
	private String reason;
	private String doctorName;

	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL)
	private List<MedOrders> medOrders;

	public int getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}

	public String getEncounterType() {
		return encounterType;
	}

	public void setEncounterType(String encounterType) {
		this.encounterType = encounterType;
	}

	public int getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(int bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<MedOrders> getMedOrders() {
		return medOrders;
	}

	public void setMedOrders(List<MedOrders> medOrders) {
		this.medOrders = medOrders;
	}
}
