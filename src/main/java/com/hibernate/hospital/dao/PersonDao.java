package com.hibernate.hospital.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import com.hibernate.hospital.dto.Encounter;
import com.hibernate.hospital.dto.Person;

public class PersonDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner scanner = new Scanner(System.in);

	// Person Data
	public void getPersonInformation() {
		System.out.println("Enter 1 for Save Person");
		System.out.println("Enter 2 for Find Person");
		System.out.println("Enter 3 for Update Person");
		System.out.println("Enter 4 for Remove Person");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			savePersonRecords();
			break;
		case 2:
			findPersonRecords();
			break;
		case 3:
			updatePersonRecords();
			break;

		case 4:
			removePersonRecords();
			break;
		}
	}

	// Save Person
	public static Person savePersonRecords() {
		System.out.println("Enter Person Name");
		String personName = scanner.next();
		System.out.println("Enter Person Age");
		int personAge = scanner.nextInt();
		System.out.println("Enter Person Blood");
		String personBlood = scanner.next();
		System.out.println("Enter Person Gender");
		String gender = scanner.next();

		Person person = new Person();
		person.setPersonName(personName);
		person.setPersonAge(personAge);
		person.setPersonBlood(personBlood);
		person.setGender(gender);

		List<Encounter> encounters = new ArrayList<Encounter>();
		person.setEncounters(encounters);

		entityTransaction.begin();
		entityManager.persist(person);
		entityTransaction.commit();
		System.out.println("Record Saved..!");
		return person;
	}

	// find person
	public static Person findPersonRecords() {
		System.out.println("Enter Encounter Id");
		int encounterId = scanner.nextInt();
		Encounter encounter = entityManager.find(Encounter.class, encounterId);
		if (encounter != null) {
			Person person = encounter.getPerson();
			System.out.println("=================Person Records=======================");
			System.out.println("Person Id :" + person.getPersonId());
			System.out.println("Person Name :" + person.getPersonName());
			System.out.println("Person Age :" + person.getPersonAge());
			System.out.println("Person Gender :" + person.getGender());
			System.out.println("Person Blood :" + person.getPersonBlood());
			System.out.println("=============================================");
			return person;
		}
		else {
			System.out.println("Person Not Exists..!");
		}
		return null;
	}

	// Update Person
	public static void updatePersonRecords() {
				System.out.println("Enter person Id");
		int personId = scanner.nextInt();
		Person person1 = entityManager.find(Person.class, personId);
		if (person1 != null) {
			System.out.println("Enter Person Age");
			int age = scanner.nextInt();
			person1.setPersonAge(age);

			entityTransaction.begin();
			entityManager.merge(person1);
			entityTransaction.commit();
			System.out.println("Data Updated..!");
		} else {
			System.out.println("Person Not Exists..!");
		}
	}

	// Remove Person
	public static boolean removePersonRecords() {
		System.out.println("Enter Person Id");
		int personId = scanner.nextInt();
		Person person = entityManager.find(Person.class, personId);
		if (person != null) {
			entityTransaction.begin();
			entityManager.remove(person);
			entityTransaction.commit();
			System.out.println("Data Removed..!");
			return true;
		} else {
			System.out.println("Person Not Exists..!");
		}
		return false;
	}
}
