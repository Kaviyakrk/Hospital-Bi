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

public class AddressDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner scanner = new Scanner(System.in);

	// Branch Data
	public void getAddressInformation() {
		System.out.println("Enter 1 for Save Address");
		System.out.println("Enter 2 for Find Address");
		System.out.println("Enter 3 for Update Address");
		System.out.println("Enter 4 for Remove Address");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			saveAddressRecords();
			break;
		case 2:
			findAddressRecords();
			break;
		case 3:
			updateAddressRecords();
			break;

		case 4:
			removeAddressRecords();
			break;
		}
	}

	// save Address
	public static Address saveAddressRecords() {
		Query query = entityManager.createQuery("select s from Branch s ");
		List<Branch> branches = query.getResultList();
		for (Branch branch : branches) {
			System.out.println(branch.getBranchId() + "." + branch.getBranchName());
		}
		System.out.println("Enter Branch Id");
		int branchId = scanner.nextInt();
		Branch branch1 = entityManager.find(Branch.class, branchId);
		if (branch1 != null) {
			System.out.println("Enter Adddress Door Number");
			int adressDoorNo = scanner.nextInt();
			System.out.println("Enter Address Street");
			scanner.nextLine();
			String addressStreet = scanner.nextLine();
			System.out.println("Enter Address City");
			String addressCity = scanner.next();
			System.out.println("Enter Address State");
			String addressState = scanner.next();
			System.out.println("Enter Pincode Number");
			int addressPincode = scanner.nextInt();

			Address address = new Address();
			address.setAdressDoorNo(adressDoorNo);
			address.setAddressStreet(addressStreet);
			address.setAddressCity(addressCity);
			address.setAddressState(addressState);
			address.setAddressPincode(addressPincode);
			branch1.setAddress(address);

			entityTransaction.begin();
			entityManager.merge(branch1);
			entityTransaction.commit();
			return address;

		} else {
			System.out.println("Hospital not found..!");
		}
		return null;
	}

	// find address
	public static Address findAddressRecords() {
		Query query = entityManager.createQuery("select s from Branch s");
		List<Branch> branches = query.getResultList();
		for (Branch branch1 : branches) {
			System.out.println(branch1.getBranchId() + "." + branch1.getBranchName());
		}
		System.out.println("Enter Branch Id");
		int branchId = scanner.nextInt();

		Branch branch = entityManager.find(Branch.class, branchId);
		if (branch != null) {
			Address address = branch.getAddress();
			System.out.println("Address Id :" + address.getAddressId());
			System.out.println("Door Number :" + address.getAdressDoorNo());
			System.out.println("Street Name :" + address.getAddressStreet());
			System.out.println("City :" + address.getAddressCity());
			System.out.println("State :" + address.getAddressState());
			System.out.println("Pincode :" + address.getAddressPincode());
			System.out.println("-------------------------------");
			return address;
		}
		return null;
	}

	// Update Address
	public static Address updateAddressRecords() {
		System.out.println("Enter 1 for Update  Address City");
		System.out.println("Enter 2 for Update Address State");
		System.out.println("Enter 3 for Update Address Street");
		System.out.println("Enter 4 for Update Pincode");
		System.out.println("Enter 5 for Exit");
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
				Address address = branch1.getAddress();
				System.out.println("Enter City");
				String city = scanner.next();
				address.setAddressCity(city);

				entityTransaction.begin();
				entityManager.merge(branch1);
				entityTransaction.commit();
			} else {
				System.out.println("Branch Id doesn't Exist..!");
			}
			break;
		case 2:
			if (branch1 != null) {
				Address address = branch1.getAddress();
				System.out.println("Enter State");
				String state = scanner.next();
				address.setAddressState(state);

				entityTransaction.begin();
				entityManager.merge(branch1);
				entityTransaction.commit();
			} else {
				System.out.println("Branch Id doesn't Exist..!");
			}
			break;
		case 3:
			if (branch1 != null) {
				Address address = branch1.getAddress();
				System.out.println("Enter Street");
				String street = scanner.next();
				address.setAddressStreet(street);

				entityTransaction.begin();
				entityManager.merge(branch1);
				entityTransaction.commit();
			} else {
				System.out.println("Branch Id doesn't Exist..!");
			}
			break;
		case 4:
			if (branch1 != null) {
				Address address = branch1.getAddress();
				System.out.println("Enter Pincode");
				int pincode = scanner.nextInt();
				address.setAddressPincode(pincode);

				entityTransaction.begin();
				entityManager.merge(branch1);
				entityTransaction.commit();
			} else {
				System.out.println("Branch Id doesn't Exist..!");
			}
			break;
		}
		return null;
	}

	// Remove Address
	public static void removeAddressRecords() {
		Query query1 = entityManager.createQuery("select s from Branch s ");
		List<Branch> branches = query1.getResultList();
		for (Branch branch : branches) {
			System.out.println(branch.getBranchId() + "." + branch.getBranchName());
		}
		System.out.println("Enter Branch Id");
		int branchId = scanner.nextInt();
		Branch branch1 = entityManager.find(Branch.class, branchId);
		Address address = branch1.getAddress();
		entityTransaction.begin();
		entityManager.remove(branch1);
		entityManager.remove(address);
		entityTransaction.commit();
	}
}
