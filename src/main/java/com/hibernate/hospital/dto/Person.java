package com.hibernate.hospital.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "person")
	@SequenceGenerator(name = "person", initialValue = 1, allocationSize = 1, sequenceName = "person_sequence")
	private int personId;
	private String personName;
	private int personAge;
	private String personBlood;
	private String gender;

	@OneToMany(mappedBy = "person")
	private List<Encounter> encounters;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}

	public String getPersonBlood() {
		return personBlood;
	}

	public void setPersonBlood(String personBlood) {
		this.personBlood = personBlood;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}

	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
}
