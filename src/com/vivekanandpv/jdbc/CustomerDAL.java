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

		try	(Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(query);

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
	}

	public static Customer getCustomer(int id) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM customers WHERE id = ?";

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();

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
	}

	public static void createCustomer(Customer customer) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO public.customers(name, email, contact, account_type) VALUES (?, ?, ?, ?)";

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			// replace these ?
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setLong(3, customer.getContact());
			preparedStatement.setString(4, customer.getAccountType());

			preparedStatement.execute();
		}
	}

	public static void updateCustomer(Customer updatedCustomer) throws ClassNotFoundException, SQLException {

		String query = "UPDATE public.customers SET name=?, email=?, contact=?, account_type=? WHERE id = ?";

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, updatedCustomer.getName());
			preparedStatement.setString(2, updatedCustomer.getEmail());
			preparedStatement.setLong(3, updatedCustomer.getContact());
			preparedStatement.setString(4, updatedCustomer.getAccountType());
			preparedStatement.setInt(5, updatedCustomer.getId());

			preparedStatement.execute();
		}
	}

	public static void deleteCustomer(int id) throws ClassNotFoundException, SQLException {
		String query = "DELETE FROM customers WHERE id = ?";

		try (Connection connection = getConnection()) {
			PreparedStatement preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		}
	}
}
