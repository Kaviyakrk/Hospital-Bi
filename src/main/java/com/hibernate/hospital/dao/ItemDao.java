package com.hibernate.hospital.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.hospital.dto.Item;
import com.hibernate.hospital.dto.MedOrders;

public class ItemDao {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("development");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();

	static Scanner scanner = new Scanner(System.in);

	// Medical Order Data
	public void getItemInformation() {
		System.out.println("Enter 1 for Save Items");
		System.out.println("Enter 2 for Find Items");
		System.out.println("Enter 3 for Update Items");
		System.out.println("Enter 4 for Remove Items");
		int choice = scanner.nextInt();

		System.out.println("Enter MedOrder Id");
		int medOrderId = scanner.nextInt();
		switch (choice) {
		case 1:
			saveItemRecords(medOrderId);
			break;
		case 2:
			findItemRecords(medOrderId);
			break;
		case 3:
			updateItemRecords();
			break;

		case 4:
			System.out.println("Enter Item Id");
			int itemId = scanner.nextInt();
			removeItemRecords(itemId);
			break;
		}
	}

	// save Items
	public static Item saveItemRecords(int medOrderId) {
		MedOrders medOrder = entityManager.find(MedOrders.class, medOrderId);

		if (medOrder != null) {
			System.out.println("Enter Item Name");
			String itemName = scanner.next();
			System.out.println("Enter Price");
			double price = scanner.nextDouble();

			Item item = new Item();
			item.setItemName(itemName);
			item.setItemPrice(price);

			// set item to medorder
			List<Item> items = medOrder.getItems();
			items.add(item);
			medOrder.setItems(items);
//			
			// set MedOrderList
			List<MedOrders> medOrders = new ArrayList<MedOrders>();
			medOrders.add(medOrder);
			item.setMedOrders(medOrders);

			entityTransaction.begin();
			entityManager.persist(item);
			entityTransaction.commit();
			System.out.println("Record Saved..!");
		} else {
			System.out.println("Not Exists..!");
		}
		return null;
	}

	// Update Item
	public static void updateItemRecords() {
		System.out.println("Enter 1 for Upadte Name");
		System.out.println("Enter 2 for Upadte Price");
		int choice = scanner.nextInt();

		System.out.println("Enter Item Id");
		int itemId = scanner.nextInt();
		Item item = entityManager.find(Item.class, itemId);
		switch (choice) {
		case 1:
			if (item != null) {
				System.out.println("Enter Item Name");
				String name = scanner.next();
				item.setItemName(name);

				entityTransaction.begin();
				entityManager.merge(item);
				entityTransaction.commit();
			} else {
				System.out.println("Item Not Exists...!");
			}
			break;

		case 2:
			if (item != null) {
				System.out.println("Enter Item Price");
				double price = scanner.nextDouble();
				item.setItemPrice(price);

				entityTransaction.begin();
				entityManager.merge(item);
				entityTransaction.commit();
			} else {
				System.out.println("Item Not Exists...!");
			}
			break;

		default:
			System.out.println("Select Valid Choice..!");
		}
	}

	// find Items
	public static Item findItemRecords(int medOrderId) {
		MedOrders medOrder = entityManager.find(MedOrders.class, medOrderId);

		if (medOrder != null) {
			List<Item> items = medOrder.getItems();

			System.out.println("======================Medical Order Records===================");
			System.out.println("MedOrder Id :" + medOrder.getMedOrderId());
			System.out.println("MedOrder Invoice Number :" + medOrder.getInvoiceNumber());
			System.out.println("Quantity :" + medOrder.getQuantity());
			System.out.println("Payment Mode :" + medOrder.getPaymentMode());
			System.out.println("==================================================");

			for (Item item : items) {
				System.out.println("====================Item Records=====================");
				System.out.println("Item Id :" + item.getItemId());
				System.out.println("Item Name :" + item.getItemName());
				System.out.println("Item Price :" + item.getItemPrice());
				System.out.println("Item Packing Date :" + item.getPackingDate());
				System.out.println("Item Expire Date :" + item.getExpiredDate());
				System.out.println("============================================");

				return item;
			}
		} else {
			System.out.println("Item Not Found...!");
		}
		return null;
	}

	// remove Items
	public static boolean removeItemRecords(int itemId) {
		// Find item based on user Input
		Item item = entityManager.find(Item.class, itemId);

		// Get the Medical Order List bcz we need to break the relation b/w both
		// medOrder and Item
		List<MedOrders> medOrders = item.getMedOrders();
		Iterator medOrder = medOrders.iterator();
		while (medOrder.hasNext()) {
			// Get each medOrder
			MedOrders medOrder1 = (MedOrders) medOrder.next();
			// get Items from the medOrder List
			List<Item> items = medOrder1.getItems();

			// get Each item
			for (int i1 = 0; i1 < items.size(); i1++) {

				// the item was removed from the MedOrder List
				if (items.get(i1).getItemId() == itemId) {
					items.remove(i1);
					System.out.println("Item Removed From MedOrder List..!");
					break;
				} else {
					System.out.println("Items Not Found..!");
				}
			}
		}
		// Remove The item from the Item table
		entityTransaction.begin();
		entityManager.remove(item);
		entityTransaction.commit();
		System.out.println("Data Removed...!");
		return false;
	}
}
