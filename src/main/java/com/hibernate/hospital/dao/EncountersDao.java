package com.hibernate.hospital.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.hibernate.hospital.dto.Branch;
import com.hibernate.hospital.dto.Encounter;
import com.hibernate.hospital.dto.MedOrder;
import com.hibernate.hospital.dto.MedOrders;
import com.hibernate.hospital.dto.Person;

public class EncountersDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner scanner = new Scanner(System.in);

	// Encounter Data
	public void getEncounterInformation() {
		System.out.println("Enter 1 for Save Encounter");
		System.out.println("Enter 2 for Find Encounter");
		System.out.println("Enter 3 for Update Encounter");
		System.out.println("Enter 4 for Remove Encounter");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:

			System.out.println("Enter Branch Id");
			int branchId = scanner.nextInt();
			saveEncounterRecords(branchId);
			break;
		case 2:
			findEncounterRecords();
			break;
		case 3:
			updateEncounterRecords();
			break;

		case 4:
			removeEncounterRecords();
			break;
		}
	}

	// save Encounter
	public static Encounter saveEncounter() {
		System.out.println("Enter Encounter Type");
		String encounterType = scanner.next();
		System.out.println("Enter Bed Number");
		int bedNumber = scanner.nextInt();
		System.out.println("Enter Doctor Name");
		String doctorName = scanner.next();
		System.out.println("Enter Reason");
		String reason = scanner.next();

		Encounter encounter1 = new Encounter();
		encounter1.setEncounterType(encounterType);
		encounter1.setBedNumber(bedNumber);
		encounter1.setDoctorName(doctorName);
		encounter1.setReason(reason);

		List<MedOrders> medOrders = new ArrayList<MedOrders>();
		encounter1.setMedOrders(medOrders);
		return encounter1;
	}

	// Save Encounters
	public static void saveEncounterRecords(int branchId) {
		Branch branch = entityManager.find(Branch.class, branchId);
		if (branch != null) {
			System.out.println("Select Choice");
			System.out.println("1.New Patient");
			System.out.println("2.Already appointed ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:// new User
				Person person = PersonDao.savePersonRecords();
				Encounter encounter1 = saveEncounter();
				// set encounter into person
				encounter1.setPerson(person);
				// set encounter into branch
				encounter1.setBranch(branch);
				// set Encounter List into branch
				List<Encounter> encounters = branch.getEncounters();
				encounters.add(encounter1);
				branch.setEncounters(encounters);
				// set encounter list into person
				List<Encounter> encounters1 = person.getEncounters();
				encounters1.add(encounter1);
				person.setEncounters(encounters1);

				entityTransaction.begin();
				entityManager.merge(branch);
				entityTransaction.commit();
				break;

			case 2:// Old One
				System.out.println("Enter Person Id");
				int personId = scanner.nextInt();
				Person person1 = entityManager.find(Person.class, personId);
				Encounter encounter2 = saveEncounter();
				encounter2.setPerson(person1);
				encounter2.setBranch(branch);
				List<Encounter> encounters3 = branch.getEncounters();
				encounters3.add(encounter2);
				branch.setEncounters(encounters3);
				List<Encounter> encounters4 = person1.getEncounters();
				encounters4.add(encounter2);
				person1.setEncounters(encounters4);

				entityTransaction.begin();
				entityManager.merge(branch);
				entityTransaction.commit();
			}
		} else {
			System.out.println("Branch Id not found..!");
		}
	}

	// Find Encounter Details
	public static void findEncounterRecords() {
		System.out.println("Enter 1 for Find Encounter Using Encounter Id");
		System.out.println("Enter 2 for Find Encounter Using MedOrder Id");
		System.out.println("Enter 3 for Find Encounter Using Person Id");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1: {
			System.out.println("Enter Encounter Id");
			int encounterId = scanner.nextInt();
			Encounter encounter = entityManager.find(Encounter.class, encounterId);
			if (encounter != null) {
				System.out.println("===================Encounter Details======================");
				System.out.println("Encounter Id:" + encounter.getEncounterId());
				System.out.println("Encounter Type :" + encounter.getEncounterType());
				System.out.println("Encounter Reason :" + encounter.getReason());
				System.out.println("Docter Name :" + encounter.getDoctorName());
				System.out.println("Encounter Bed :" + encounter.getBedNumber());
				System.out.println("===========================================================");
			} else {
				System.out.println("Encounter Id doesn't Exist");
			}
		}
			break;
		case 2: {
			System.out.println("Enter MedOrder Id");
			int medOrderId = scanner.nextInt();
			MedOrders medOrder = entityManager.find(MedOrders.class, medOrderId);
			Encounter encounter1 = medOrder.getEncounter();

			if (encounter1 != null) {
				System.out.println("===================Encounter Details======================");
				System.out.println("Encounter Id:" + encounter1.getEncounterId());
				System.out.println("Encounter Type :" + encounter1.getEncounterType());
				System.out.println("Encounter Reason :" + encounter1.getReason());
				System.out.println("Docter Name :" + encounter1.getDoctorName());
				System.out.println("Encounter Bed :" + encounter1.getBedNumber());
				System.out.println("===========================================================");
			} else {
				System.out.println("Medical Order Id Doesn't Exists..!");
			}
		}
			break;

		case 3: {
			System.out.println("Enter Branch Id");
			int branchId = scanner.nextInt();
			Branch branch = entityManager.find(Branch.class, branchId);
			List<Encounter> encounters = branch.getEncounters();

			if (encounters != null) {
				for (Encounter encounter : encounters) {
					System.out.println("===================Encounter Details======================");
					System.out.println("Encounter Id:" + encounter.getEncounterId());
					System.out.println("Encounter Type :" + encounter.getEncounterType());
					System.out.println("Encounter Reason :" + encounter.getReason());
					System.out.println("Docter Name :" + encounter.getDoctorName());
					System.out.println("Encounter Bed :" + encounter.getBedNumber());
					System.out.println("===========================================================");
				}
			} else {
				System.out.println("Branch Id doesn't Exists..!");
			}
		}
			break;
		}
	}

	// Update Encounter Details
	public static void updateEncounterRecords() {
		System.out.println("Enter 1 for Upadte Type");
		System.out.println("Enter 2 for Upadte Reason");
		System.out.println("Enter 3 for Update Bed Number");
		System.out.println("Enter 4 for Update Doctor Name");
		int choice = scanner.nextInt();

		Query query = entityManager.createQuery("select s from Encounter s");
		List<Encounter> encounters = query.getResultList();
		for (Encounter encounter : encounters) {
			System.out.println(encounter.getEncounterId() + "." + encounter.getEncounterType());
		}

		System.out.println("Enter Encounter id");
		int encounterId = scanner.nextInt();

		switch (choice) {
		case 1:
			System.out.println("Enter Type");
			String type = scanner.next();
			Encounter encounter = entityManager.find(Encounter.class, encounterId);
			if (encounter != null) {
				encounter.setEncounterType(type);
				entityTransaction.begin();
				entityManager.merge(encounter);
				entityTransaction.commit();
				System.out.println("Updated..!");
			} else {
				System.out.println("Id not Found..!");
			}
			break;

		case 2:
			System.out.println("Enter Reason");
			String reason = scanner.next();
			Encounter encounter1 = entityManager.find(Encounter.class, encounterId);
			if (encounter1 != null) {
				encounter1.setReason(reason);
				entityTransaction.begin();
				entityManager.merge(encounter1);
				entityTransaction.commit();
				System.out.println("Updated..!");
			} else {
				System.out.println("Id not Found..!");
			}
			break;

		case 3:
			System.out.println("Enter Bed NO");
			int bedNo = scanner.nextInt();
			Encounter encounter2 = entityManager.find(Encounter.class, encounterId);
			if (encounter2 != null) {
				encounter2.setBedNumber(bedNo);
				entityTransaction.begin();
				entityManager.merge(encounter2);
				entityTransaction.commit();
				System.out.println("Updated..!");
			} else {
				System.out.println("Id not Found..!");
			}
			break;

		case 4:
			System.out.println("Enter Doctor Name");
			String doctorName = scanner.next();
			Encounter encounter3 = entityManager.find(Encounter.class, encounterId);
			if (encounter3 != null) {
				encounter3.setDoctorName(doctorName);
				entityTransaction.begin();
				entityManager.merge(encounter3);
				entityTransaction.commit();
				System.out.println("Updated..!");
			} else {
				System.out.println("Id not Found..!");
			}
			break;
		}
	}

	// Remove Encounter
	public static boolean removeEncounterRecords() {
		System.out.println("Enter Encounter Id");
		int encounterId = scanner.nextInt();
		Encounter encounter = entityManager.find(Encounter.class, encounterId);
		if (encounter != null) {
			entityTransaction.begin();
			entityManager.remove(encounter);
			entityTransaction.commit();
			System.out.println("Data Removed..!");
			return true;
		} else {
			System.out.println("Id Not Exists..!");
		}
		return false;
	}
}
