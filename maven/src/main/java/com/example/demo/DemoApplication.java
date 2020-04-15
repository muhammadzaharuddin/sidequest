package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.sql.*;

@SpringBootApplication
public class DemoApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}

	public static void createDatabase(String url, String databaseName) {

		try(Connection connection = DriverManager.getConnection(url)) {
			if (connection != null) {
				DatabaseMetaData databaseMetaData = connection.getMetaData();
				System.out.println("\nDriver name: " + databaseMetaData.getDriverName());
				System.out.println("Database created: " + databaseName);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void createTable(String url) {

		String tableName = "pet";
		String query = "DROP TABLE IF EXISTS " + tableName;

		try(Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.executeUpdate();
			System.out.println("\nTable dropped (if exists): " + tableName);
		} catch (SQLException e) {
			System.out.println("\n" + e.getMessage());
		}

		query = "CREATE TABLE " + tableName + " ("
				+ "id integer PRIMARY KEY AUTOINCREMENT,"
				+ "type varchar(30) NOT NULL,"
				+ "gender varchar(30) NOT NULL,"
				+ "age integer NOT NULL"
				+ ");";

		try(Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement()) {
			statement.execute(query);
			System.out.println("\nTable created: " + tableName);
		} catch (SQLException e) {
			System.out.println("\n" + e.getMessage());
		}

	}

	public static void initializeTable(String url) {

		String query = "INSERT INTO pet (type, gender, age) values"
				+ "('cat', 'female', 2),"
				+ "('dog', 'male', 8),"
				+ "('rabbit', 'male', 4),"
				+ "('hamster', 'female', 1)";

		try(Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.executeUpdate();
			System.out.println("\nInitialize table with test data.\n");
		} catch (SQLException e) {
			System.out.println("\n" + e.getMessage());
		}

	}

	public static void readAllPet(String url) {

		String query = "SELECT * FROM pet;";

		try(Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query)) {

			System.out.println("ALL PETS: \n");
			System.out.println("id \t" + "type \t" + "gender \t" + "age\n");
			while (resultSet.next()){
				System.out.println(resultSet.getInt("id") + "\t"
						+ resultSet.getString("type") + "\t"
						+ resultSet.getString("gender") + "\t"
						+ resultSet.getInt("age"));
			}
			System.out.println();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void insertPet(String url) {

		Scanner scanner = new Scanner(System.in);
		String type = "";
		String gender = "";
		int age = 0;

		try {

			System.out.print("\nEnter pet type: ");
			type = scanner.nextLine();

			System.out.print("Enter pet gender: ");
			gender = scanner.nextLine();

			System.out.print("Enter pet age: ");
			age = scanner.nextInt();
			scanner.nextLine();

			String query = "INSERT INTO pet (type, gender, age) values (?,?,?)";

			try(Connection connection = DriverManager.getConnection(url);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, type);
				preparedStatement.setString(2, gender);
				preparedStatement.setInt(3, age);
				preparedStatement.executeUpdate();
				System.out.println("\nSuccesfully added pet.\n");
			} catch (SQLException e) {
				System.out.println("\nFailed to add pet. ");
				System.out.println(e.getMessage() + "\n");
			}

		} catch (Exception exc) {
			System.out.println("\n Failed to add pet. ");
			System.out.println("Error: " + exc + "\n");
		}

	}

	public static void updatePet(String url) {

		Scanner scanner = new Scanner(System.in);
		int id = 0;
		String type = "";
		String gender = "";
		int age = 0;

		try {

			System.out.print("\nEnter pet id: ");
			id = scanner.nextInt();
			scanner.nextLine();

			System.out.print("Enter pet type: ");
			type = scanner.nextLine();

			System.out.print("Enter pet gender: ");
			gender = scanner.nextLine();

			System.out.print("Enter pet age: ");
			age = scanner.nextInt();
			scanner.nextLine();

			String query = "UPDATE pet SET type = ?, "
					+ "gender = ?,"
					+ "age = ?"
					+ "WHERE id = ?";

			try(Connection connection = DriverManager.getConnection(url);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, type);
				preparedStatement.setString(2, gender);
				preparedStatement.setInt(3, age);
				preparedStatement.setInt(4, id);
				preparedStatement.executeUpdate();
				System.out.println("\nSuccesfully updated pet.\n");
			} catch (SQLException e) {
				System.out.println("\nFailed to update pet. ");
				System.out.println(e.getMessage() + "\n");
			}

		} catch (Exception exc) {
			System.out.println("\nFailed to update pet. ");
			System.out.println("Error: " + exc + "\n");
		}

	}

	public static void deletePet(String url) {

		Scanner scanner = new Scanner(System.in);
		int id = 0;

		try {

			System.out.print("\nEnter pet id: ");
			id = scanner.nextInt();

			String query = "DELETE FROM pet WHERE id = ?";

			try(Connection connection = DriverManager.getConnection(url);
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				System.out.println("\nSuccesfully deleted pet.\n");
			} catch (SQLException e) {
				System.out.println("\nFailed to delete pet. ");
				System.out.println(e.getMessage() + "\n");
			}

		} catch (Exception exc) {
			System.out.println("\nFailed to delete pet. ");
			System.out.println("Error: " + exc + "\n");
		}

	}

	public static void main(String[] args){

		System.out.println("\nInitializing database...");
		String databaseName = "petDatabase.db";
		String url = "jdbc:sqlite:" + databaseName;
		createDatabase(url, databaseName);
		createTable(url);
		initializeTable(url);
		readAllPet(url);
		System.out.println("\nDatabase initialized");

		String option = "";
		Scanner scanner = new Scanner(System.in);

		do {

			System.out.println("\nOptions: "
					+ "\n1 - View all pet"
					+ "\n2 - Insert pet"
					+ "\n3 - Update pet"
					+ "\n4 - Delete pet"
					+ "\n5 - Exit"
					+ "\n\nPlease select option: ");
			option = scanner.nextLine();

			if (option.equals("1")) {
				readAllPet(url);
			} else if (option.equals("2")) {
				insertPet(url);
				readAllPet(url);
			} else if (option.equals("3")) {
				updatePet(url);
				readAllPet(url);
			} else if (option.equals("4")) {
				deletePet(url);
				readAllPet(url);
			} else if (option.equals("5")) {
				System.out.println("\nExiting program.\n");
				break;
			} else {
				System.out.println("\nInvalid option. Please choose again.\n");
			}

		} while (!option.equals("5"));

		scanner.close();

	}

}
