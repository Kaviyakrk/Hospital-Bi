package com.hibernate.hospital.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.hospital.dto.Encounter;
import com.hibernate.hospital.dto.Item;
import com.hibernate.hospital.dto.MedOrders;

public class MedOrdersDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner scanner = new Scanner(System.in);

	// Medical Order Data
	public void getMedicalInformation() {
		System.out.println("Enter 1 for Save Medical Orders");
		System.out.println("Enter 2 for Find Medical Orders");
		System.out.println("Enter 3 for Update Medical Orders");
		System.out.println("Enter 4 for Remove Medical Orders");
		int choice = scanner.nextInt();

		System.out.println("Enter Encounter Id");
		int encounterId = scanner.nextInt();
		switch (choice) {
		case 1:
			saveMedOrderRecords(encounterId);
			break;
		case 2:
			findMedOrderRecords(encounterId);
			break;
		case 3:
			updateMedOrderRecords(encounterId);
			break;

		case 4:
			removeMedOrderRecords();
			break;
		}
	}

	// save Medical Orders
	public static MedOrders saveMedOrderRecords(int encounterId) {
		Encounter encounter = entityManager.find(Encounter.class, encounterId);

		if (encounter != null) {
			System.out.println("Enter Quantity ");
			int quantity = scanner.nextInt();
			System.out.println("Enter Invoice Number");
			String invoiceNo = scanner.next();
			System.out.println("Enter Payment Mode");
			scanner.nextLine();
			String payment = scanner.nextLine();

			MedOrders medOrder = new MedOrders();
			medOrder.setQuantity(quantity);
			medOrder.setInvoiceNumber(invoiceNo);
			medOrder.setPaymentMode(payment);

			// set items
			List<Item> items = new ArrayList<Item>();
			medOrder.setItems(items);

			// set med order list to encounter
			List<MedOrders> medOrders = encounter.getMedOrders();
			medOrders.add(medOrder);
			encounter.setMedOrders(medOrders);

			// set encounter to medorder
			medOrder.setEncounter(encounter);

			entityTransaction.begin();
			entityManager.merge(encounter);
			entityTransaction.commit();
			System.out.println("Record Saved..!");
			return medOrder;
		}
		else {
			System.out.println("Not Exists...!");
		}
		return null;
	}

	// Find MedOrder
	public static MedOrders findMedOrderRecords(int encounterId) {
		Encounter encounter = entityManager.find(Encounter.class, encounterId);
		if (encounter != null) {
			List<MedOrders> medOrders = encounter.getMedOrders();

			for (MedOrders medOrder : medOrders) {
				System.out.println("======================Medical Orders===========================");
				System.out.println("Medical Order Id :" + medOrder.getMedOrderId());
				System.out.println("Invoice Number :" + medOrder.getInvoiceNumber());
				System.out.println("Quantity :" + medOrder.getQuantity());
				System.out.println("Payment Mode :" + medOrder.getPaymentMode());
				System.out.println("=======================================");
				return medOrder;
			}
		}
		return null;
	}

	// Update MedOrder
	public static void updateMedOrderRecords(int medOrderId) {
		System.out.println("Enter 1 for Upadte Invoice Number");
		System.out.println("Enter 2 for Upadte Quantity");
		System.out.println("Enter 3 for Update Payment Mode");
		int choice = scanner.nextInt();

		MedOrders medOrder = entityManager.find(MedOrders.class, medOrderId);
		switch (choice) {
		case 1:
			if (medOrder != null) {
				System.out.println("Enter Invoice Number");
				String invoiceNo = scanner.next();
				medOrder.setInvoiceNumber(invoiceNo);

				entityTransaction.begin();
				entityManager.merge(medOrder);
				entityTransaction.commit();
				System.out.println("Data Updated..!");
			} else {
				System.out.println("Record Not Found..!");
			}
			break;
		case 2:
			if (medOrder != null) {
				System.out.println("Enter Quantity");
				int quantity = scanner.nextInt();
				medOrder.setQuantity(quantity);
				;

				entityTransaction.begin();
				entityManager.merge(medOrder);
				entityTransaction.commit();
				System.out.println("Data Updated..!");
			} else {
				System.out.println("Record Not Found..!");
			}
			break;
		case 3:
			if (medOrder != null) {
				System.out.println("Enter Payment Mode");
				scanner.nextLine();
				String paymentMode = scanner.nextLine();
				medOrder.setPaymentMode(paymentMode);
				entityTransaction.begin();
				entityManager.merge(medOrder);
				entityTransaction.commit();
				System.out.println("Data Updated..!");
			} else {
				System.out.println("Record Not Found..!");
			}
			break;
		default: {
			System.out.println("Enter Valid Choice..!");
		}
		}
	}

	// Remove MedOrder
	public static boolean removeMedOrderRecords() {
		System.out.println("Enter MedOrder Id");
		int medOrderId = scanner.nextInt();
		MedOrders medOrder = entityManager.find(MedOrders.class, medOrderId);
		if (medOrder != null) {
			entityTransaction.begin();
			entityManager.remove(medOrder);
			entityTransaction.commit();
			System.out.println("Data Removed..!");
			return true;
		} else {
			System.out.println("MedOrder Not Exists..!");
		}
		return false;
	}
}
