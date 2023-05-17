package com.vivekanandpv.jdbc;

import java.util.Scanner;

public class Menu {
	private final Bank bank;
	
	public Menu() {
		this.bank = new Bank();
	}
	
	public void drive() {

		while (true) {
			showMenu();

			Scanner scanner = new Scanner(System.in);
			try {

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					this.addNewCustomer();
					break;
				case 2:
					this.displayListOfCustomers();
					break;
				case 3:
					this.searchCustomer();
					break;
				case 4:
					this.deleteCustomer();
					break;
				case 5:
					System.out.println("Exiting the application. Bye.");
					return;

				default:
					System.out.println("Invalid option selected. Try again");
					break;
				}
			} catch (RuntimeException ex) {
				System.out.println("Invalid input. Only numbers: 1 to 5");
			}
		}
	}

	public void showMenu() {
		System.out.println("Welcome to ABCD Bank");
		System.out.println("Please enter your choice");
		System.out.println("1. for Add new customer");
		System.out.println("2. for Display customers");
		System.out.println("3. for Search customer");
		System.out.println("4. for Delete customer");
		System.out.println("5. for Exit the bank application");
	}
	
	
	
	public void addNewCustomer() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Adding a new Customer:");
			System.out.println("*****************************");
			System.out.println("Enter Name: ");
			String name = scanner.nextLine();
			System.out.println("Enter Email: ");
			String email = scanner.nextLine();
			System.out.println("Enter Contact: ");
			long contact = Long.parseLong(scanner.nextLine());
			System.out.println("Enter Account Type: ");
			String accountType = scanner.nextLine();
			
			bank.addCustomer(name, email, contact, accountType);
		} catch (RuntimeException re) {
			System.out.println("Invalid input. Only numbers.");
		}
	}
	
	public void searchCustomer() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Enter the Customer ID (Search):");
			int id = Integer.parseInt(scanner.nextLine());
			bank.displayCustomer(id);
		} catch (RuntimeException re) {
			System.out.println("Invalid input. Only numbers.");
		}
	}
	
	public void deleteCustomer() {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("Enter the Customer ID (Delete):");
			int id = Integer.parseInt(scanner.nextLine());
			bank.deleteCustomer(id);
		} catch (RuntimeException re) {
			System.out.println("Invalid input. Only numbers.");
		}
	}
	
	public void displayListOfCustomers() {
		System.out.println("All Customers:");
		bank.displayListOfCustomers();
	}

}
