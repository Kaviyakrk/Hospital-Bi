package com.hibernate.hospital.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.hibernate.hospital.dto.Address;
import com.hibernate.hospital.dto.Branch;
import com.hibernate.hospital.dto.Hospital;

public class HospitalDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner scanner = new Scanner(System.in);

	// Hospital
	public void getHospitalInformation() {
		System.out.println("Enter 1 for Save Hospital");
		System.out.println("Enter 2 for Find Hospital");
		System.out.println("Enter 3 for Update Hospital");
		System.out.println("Enter 4 for Remove Hospital");
		System.out.println("Enter 5 for Find All Records");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			saveHospitalRecords();
			break;
		case 2:
			findHospitalRecords();
			break;
		case 3:
			updateHospitalRecords();
			break;

		case 4:
			removeHospitalRecords();
			break;

		case 5:
			findAllRecords();
			break;
		}
	}

	// save Hospital only
	public Hospital saveHospitalRecords() {
		System.out.println("Enter Hospital Name");
		String hospitalName = scanner.next();
		System.out.println("Enter Hospital Type");
		scanner.nextLine();
		String hospitalType = scanner.nextLine();
		System.out.println("Enter Hospital Email");
		String hospitalemail = scanner.next();
		System.out.println("Enter hospitalContactNo");
		long hospitalContactNo = scanner.nextLong();

		Hospital hospital = new Hospital();
		hospital.setHospitalName(hospitalName);
		hospital.setHospitalType(hospitalType);
		hospital.setHospitalemail(hospitalemail);
		hospital.setHospitalContactNo(hospitalContactNo);

		List<Branch> branches = new ArrayList<Branch>();
		hospital.setBranches(branches);

		entityTransaction.begin();
		entityManager.persist(hospital);
		entityTransaction.commit();
		System.out.println("Data Saved..!");
		return hospital;
	}

	// Find Hospital
	public static Hospital findHospitalRecords() {
		Query query = entityManager.createQuery("select s from Hospital s");
		List<Hospital> hospitals = query.getResultList();
		for (Hospital hospital : hospitals) {
			System.out.println(hospital.getHospitalId() + "." + hospital.getHospitalName());
		}
		System.out.println("Enter Hospital Id");
		int hospitalId = scanner.nextInt();

		Hospital hospital1 = entityManager.find(Hospital.class, hospitalId);
		if (hospital1 != null) {
			System.out.println("------------------Hospital Records----------------------");
			System.out.println("Hospital Id :" + hospital1.getHospitalId());
			System.out.println("Hospital Name :" + hospital1.getHospitalName());
			System.out.println("Hospital Type :" + hospital1.getHospitalType());
			System.out.println("Hospital Email :" + hospital1.getHospitalemail());
			System.out.println("Hospital Contact No :" + hospital1.getHospitalContactNo());
			System.out.println("-----------------------------------------");
			return hospital1;
		}
		else {
			System.out.println("Record Not Found..!");
		}
		return null;
	}

	// Update Hospital records
	public static Hospital updateHospitalRecords() {
		System.out.println("Enter 1 for Update Hospital Name");
		System.out.println("Enter 2 for Update Hospital Contact No");
		System.out.println("Enter 3 for Update Hospital Email");
		System.out.println("Enter 4 for Update Hospital Hospital Type ");
		System.out.println("Enter 5 for Exit");
		int choice = scanner.nextInt();

		Query query = entityManager.createQuery("select s from Hospital s ");
		List<Hospital> hospitals = query.getResultList();
		for (Hospital h : hospitals) {
			System.out.println(h.getHospitalId() + "." + h.getHospitalName());
		}

		System.out.println("Enter Hospital Id");
		int hospitalId = scanner.nextInt();
		Hospital hospital1 = entityManager.find(Hospital.class, hospitalId);

		switch (choice) {
		case 1:
			if (hospital1 != null) {
				System.out.println("Enter Hospital Name");
				String hospitalName = scanner.next();
				hospital1.setHospitalName(hospitalName);

				entityTransaction.begin();
				entityManager.merge(hospital1);
				entityTransaction.commit();
				System.out.println("Data Updated..!");
				return hospital1;
			} else {
				System.out.println("Hospital id Doesn't exist..!");
			}
			break;
		case 2:
			if (hospital1 != null) {
				System.out.println("Enter Hospital Contact No");
				long contactNo = scanner.nextLong();
				hospital1.setHospitalContactNo(contactNo);

				entityTransaction.begin();
				entityManager.merge(hospital1);
				entityTransaction.commit();
				System.out.println("Data Updated..!");
				return hospital1;
			} else {
				System.out.println("Hospital id Doesn't exist..!");
			}
			break;
		case 3:
			if (hospital1 != null) {
				System.out.println("Enter Hospital Email");
				String hospitalEmail = scanner.next();
				hospital1.setHospitalemail(hospitalEmail);

				entityTransaction.begin();
				entityManager.merge(hospital1);
				entityTransaction.commit();
				System.out.println("Data Updated..!");
				return hospital1;
			} else {
				System.out.println("Hospital id Doesn't exist..!");
			}
			break;
		case 4:
			if (hospital1 != null) {
				System.out.println("Enter Hospital Type");
				String hospitalType = scanner.next();
				hospital1.setHospitalType(hospitalType);

				entityTransaction.begin();
				entityManager.merge(hospital1);
				entityTransaction.commit();
				System.out.println("Data Updated..!");
				return hospital1;
			} else {
				System.out.println("Hospital id Doesn't exist..!");
			}
			break;

		}

		return null;
	}

	// Remove Hospital
	public static void removeHospitalRecords() {
		Query query1 = entityManager.createQuery("select s from Hospital s ");
		List<Hospital> hospital = query1.getResultList();
		for (Hospital hospital1 : hospital) {
			System.out.println(hospital1.getHospitalId() + "." + hospital1.getHospitalName());
		}
		System.out.println("Enter Hospital Id");
		int hospitalId = scanner.nextInt();
		Hospital hospital2 = entityManager.find(Hospital.class, hospitalId);

		entityTransaction.begin();
		entityManager.remove(hospital2);
		entityTransaction.commit();
	}

	// find all records
	public static void findAllRecords() {
		Query query = entityManager.createQuery("select s from Branch s");
		List<Branch> branches = query.getResultList();
		for (Branch branch1 : branches) {
			System.out.println(branch1.getBranchId() + "." + branch1.getBranchName());
		}
		System.out.println("Enter Branch Id");
		int branchId = scanner.nextInt();

		Branch branch = entityManager.find(Branch.class, branchId);
		if (branch != null) {
			Hospital hospital1 = branch.getHospital();
			System.out.println("================Hospital================");
			System.out.println("Hospital Id :" + hospital1.getHospitalId());
			System.out.println("Hospital Name :" + hospital1.getHospitalName());
			System.out.println("Hospital Type :" + hospital1.getHospitalType());
			System.out.println("Hospital Email :" + hospital1.getHospitalemail());
			System.out.println("Hospital Contact No :" + hospital1.getHospitalContactNo());
			System.out.println("-----------------------------------------");

			System.out.println("================Branch================");
			System.out.println("Branch Id :" + branch.getBranchId());
			System.out.println("Branch Name :" + branch.getBranchName());
			System.out.println("Branch Type :" + branch.getBranchCity());
			System.out.println("Branch Email :" + branch.getBranchEmail());
			System.out.println("Branch Contact No :" + branch.getBranchContactNo());
			System.out.println("-----------------------------------------");

			Address address = branch.getAddress();
			System.out.println("================Address================");
			System.out.println("Address Id :" + address.getAddressId());
			System.out.println("Door Number :" + address.getAdressDoorNo());
			System.out.println("Street Name :" + address.getAddressStreet());
			System.out.println("City :" + address.getAddressCity());
			System.out.println("State :" + address.getAddressState());
			System.out.println("Pincode :" + address.getAddressPincode());
			System.out.println("-------------------------------------");
		}
	}
}