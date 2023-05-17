package com.vivekanandpv.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAL {
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		Connection conn = null;

		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_demo", "postgres", "postgres");
		if (conn != null) {
			return conn;
		} else {
			throw new RuntimeException("Connection error");
		}
	}

	public static List<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
		List<Customer> customers = new ArrayList<>();

		String query = "SELECT * FROM customers";

		Statement stmt = getConnection().createStatement();
		ResultSet results = stmt.executeQuery(query);

		while (results.next()) {
			Customer customer = new Customer();

			customer.setId(results.getInt("id"));
			customer.setName(results.getString("name"));
			customer.setEmail(results.getString("email"));
			customer.setContact(results.getLong("contact"));
			customer.setAccountType(results.getString("account_type"));

			customers.add(customer);
		}

		return customers;
	}

	public static Customer getCustomer(int id) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM customers WHERE id = ?";

		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet results = pstmt.executeQuery();

		while (results.next()) {
			Customer customer = new Customer();

			customer.setId(results.getInt("id"));
			customer.setName(results.getString("name"));
			customer.setEmail(results.getString("email"));
			customer.setContact(results.getLong("contact"));
			customer.setAccountType(results.getString("account_type"));

			return customer;
		}

		throw new RuntimeException("Customer doesn't exist"); // customer doesn't exist

	}

	public static void createCustomer(Customer customer) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO public.customers(name, email, contact, account_type) VALUES (?, ?, ?, ?)";

		PreparedStatement pstmt = getConnection().prepareStatement(sql);

		// replace these ?
		pstmt.setString(1, customer.getName());
		pstmt.setString(2, customer.getEmail());
		pstmt.setLong(3, customer.getContact());
		pstmt.setString(4, customer.getAccountType());

		pstmt.execute();
	}

	public static void updateCustomer(Customer updatedCustomer) throws ClassNotFoundException, SQLException {

		String query = "UPDATE public.customers SET name=?, email=?, contact=?, account_type=? WHERE id = ?";

		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setString(1, updatedCustomer.getName());
		pstmt.setString(2, updatedCustomer.getEmail());
		pstmt.setLong(3, updatedCustomer.getContact());
		pstmt.setString(4, updatedCustomer.getAccountType());
		pstmt.setInt(5, updatedCustomer.getId());

		pstmt.execute();
	}

	public static void deleteCustomer(int id) throws ClassNotFoundException, SQLException {
		String query = "DELETE FROM customers WHERE id = ?";

		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.execute();
	}
}
