package com.ospital.hibernate.controller;

import java.util.Scanner;

import com.hibernate.hospital.dao.AddressDao;
import com.hibernate.hospital.dao.BranchDao;
import com.hibernate.hospital.dao.EncountersDao;
import com.hibernate.hospital.dao.HospitalDao;
import com.hibernate.hospital.dao.ItemDao;
import com.hibernate.hospital.dao.MedOrdersDao;
import com.hibernate.hospital.dao.PersonDao;

public class HospitalController {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean loop = true;
		while (loop) {
			int choice = getChoiceFromUser();
			switch (choice) {
			case 1:
				new HospitalDao().getHospitalInformation();
				break;
			case 2:
				new BranchDao().getBranchInformation();
				break;

			case 3:
				new AddressDao().getAddressInformation();
				break;

			case 4:
				new EncountersDao().getEncounterInformation();
				break;

			case 5:
				new PersonDao().getPersonInformation();
				break;

			case 6:
				new MedOrdersDao().getMedicalInformation();
				break;

			case 7:
				new ItemDao().getItemInformation();
				break;

			case 8:
				loop = false;
				System.out.println("Exit Loop...!");
				break;
			default:
				System.out.println("Enter Valid Choice..!");
			}
		}
	}

	// get choice from user
	public static int getChoiceFromUser() {
		System.out.println("Enter 1 for Hospital Records");
		System.out.println("Enter 2 for Branch Records");
		System.out.println("Enter 3 for Address Records");
		System.out.println("Enter 4 for Encounter Records ");
		System.out.println("Enter 5 for Person Information ");
		System.out.println("Enter 6 for Medical Order Information");
		System.out.println("Enter 7 for Item Information");
		System.out.println("Enter 8 for Exit");
		int choice = scanner.nextInt();
		return choice;
	}
}
