package com.hibernate.hospital.dao;

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

public class BranchDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner scanner = new Scanner(System.in);

	// Branch Data
	public void getBranchInformation() {
		System.out.println("Enter 1 for Save Branch");
		System.out.println("Enter 2 for Find Branch");
		System.out.println("Enter 3 for Update Branch");
		System.out.println("Enter 4 for Remove Branch");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			saveBranchRecords();
			break;
		case 2:
			findBranchRecords();
			break;
		case 3:
			updateBranchRecords();
			break;

		case 4:
			removeBranchRecords();
			break;
		}
	}

	public static void saveBranchRecords() {
		Query query = entityManager.createQuery("select s from Hospital s ");
		List<Hospital> hospitals = query.getResultList();
		for (Hospital h : hospitals) {
			System.out.println(h.getHospitalId() + "." + h.getHospitalName());
		}
		System.out.println("Enter Hospital Id");
		int hospitalId = scanner.nextInt();
		Hospital hospital1 = entityManager.find(Hospital.class, hospitalId);
		if (hospital1 != null) {
			System.out.println("Enter branch Name");
			String branchName = scanner.next();
			System.out.println("Enter branch City");
			String branchCity = scanner.next();
			System.out.println("Enter branch Email");
			String branchEmail = scanner.next();
			System.out.println("Enter branch Contact Number");
			long branchContactNo = scanner.nextLong();

			Branch branch1 = new Branch();

			branch1.setBranchName(branchName);
			branch1.setBranchCity(branchCity);
			branch1.setBranchEmail(branchEmail);
			branch1.setBranchContactNo(branchContactNo);
			List<Branch> branches = hospital1.getBranches();
			branches.add(branch1);
			branch1.setHospital(hospital1);
			hospital1.setBranches(branches);

			Address address=AddressDao.saveAddressRecords();
			branch1.setAddress(address);

			entityTransaction.begin();
			entityManager.persist(branch1);
			entityTransaction.commit();

			entityTransaction.begin();
			entityManager.merge(hospital1);
			entityTransaction.commit();
		} else {
			System.out.println("Hospital not found..!");
		}
	}

	// find Branch
	public static void findBranchRecords() {
		Query query = entityManager.createQuery("select s from Address s");
		List<Address> address = query.getResultList();
		for (Address address1 : address) {
			System.out.println(address1.getAddressId() + "." + address1.getAddressCity());
		}
		System.out.println("Enter Address Id");
		int addressId = scanner.nextInt();

		Address address2 = entityManager.find(Address.class, addressId);
		if (address2 != null) {
			Branch branch = address2.getBranch();
			System.out.println("Branch Id :" + branch.getBranchId());
			System.out.println("Branch Name :" + branch.getBranchName());
			System.out.println("Branch Type :" + branch.getBranchCity());
			System.out.println("Branch Email :" + branch.getBranchEmail());
			System.out.println("Branch Contact No :" + branch.getBranchContactNo());
			System.out.println("-----------------------------------------");
		}
	}

	// Update Branch
	public static Branch updateBranchRecords() {
		System.out.println("Enter 1 for Update Branch Name");
		System.out.println("Enter 2 for Update Branch Contact No");
		System.out.println("Enter 3 for Update Branch Email");
		System.out.println("Enter 4 for Exit");
		int choice = scanner.nextInt();

		Query query1 = entityManager.createQuery("select s from Branch s ");
		List<Branch> branches = query1.getResultList();
		for (Branch branch : branches) {
			System.out.println(branch.getBranchId() + "." + branch.getBranchName());
		}
		System.out.println("Enter Branch Id");
		int branchId = scanner.nextInt();
		Branch branch1 = entityManager.find(Branch.class, branchId);

		switch (choice) {
		case 1:
			if (branch1 != null) {
				System.out.println("Enter Branch Name");
				String branchName = scanner.next();
				branch1.setBranchName(branchName);
				branches.add(branch1);

				entityTransaction.begin();
				entityManager.merge(branch1);
				entityTransaction.commit();
				return branch1;
			} else {
				System.out.println("Branch id Doesn't exist..!");
			}
			break;

		case 2:
			if (branch1 != null) {
				System.out.println("Enter Branch Contact Number");
				long branchContactNo = scanner.nextLong();
				branch1.setBranchContactNo(branchContactNo);
				branches.add(branch1);

				entityTransaction.begin();
				entityManager.merge(branch1);
				entityTransaction.commit();
				return branch1;
			} else {
				System.out.println("Branch id Doesn't exist..!");
			}
			break;

		case 3:
			if (branch1 != null) {
				System.out.println("Enter Branch email");
				String branchEmail = scanner.next();
				branch1.setBranchEmail(branchEmail);
				branches.add(branch1);

				entityTransaction.begin();
				entityManager.merge(branch1);
				entityTransaction.commit();
				return branch1;
			} else {
				System.out.println("Branch id Doesn't exist..!");
			}
			break;
		}
		return null;
	}

	// remove branch
	public static void removeBranchRecords() {
		Query query1 = entityManager.createQuery("select s from Branch s ");
		List<Branch> branches = query1.getResultList();
		for (Branch branch : branches) {
			System.out.println(branch.getBranchId() + "." + branch.getBranchName());
		}
		System.out.println("Enter Branch Id");
		int branchId = scanner.nextInt();
		Branch branch1 = entityManager.find(Branch.class, branchId);

		entityTransaction.begin();
		entityManager.remove(branch1);
		entityTransaction.commit();
	}
}
