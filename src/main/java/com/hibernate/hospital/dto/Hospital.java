package com.hibernate.hospital.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "hospital")
	@SequenceGenerator(name = "hospital", initialValue = 1, allocationSize = 1, sequenceName = "hospital_sequence")
	private int hospitalId;
	private String hospitalName;
	private String hospitalType;
	private String hospitalemail;
	private long hospitalContactNo;

	@OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
	private List<Branch> branches;

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getHospitalemail() {
		return hospitalemail;
	}
	public void setHospitalemail(String hospitalemail) {
		this.hospitalemail = hospitalemail;
	}

	public long getHospitalContactNo() {
		return hospitalContactNo;
	}

	public void setHospitalContactNo(long hospitalContactNo) {
		this.hospitalContactNo = hospitalContactNo;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
}
