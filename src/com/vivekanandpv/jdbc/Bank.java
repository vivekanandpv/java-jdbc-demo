package com.vivekanandpv.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bank {
	public void addCustomer(String name, String email, long contact, String accountType) {
		Customer customer = new Customer(name, email, contact, accountType);
		try {
			CustomerDAL.createCustomer(customer);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void displayListOfCustomers() {
		try {
			for (Customer customer : CustomerDAL.getAllCustomers()) {
				System.out.println(customer.toString());
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void displayCustomer(int id) {
		try {
			Customer customer = CustomerDAL.getCustomer(id);
			System.out.println(customer.toString());
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (RuntimeException re) {
			System.out.println("Error: " + re.getMessage());
		}
	}

	public void deleteCustomer(int id) {
		try {
			CustomerDAL.deleteCustomer(id);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
