package com.group09.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Philippe
 * 
 */
public class Database {
	private String DBPath = "";
	private Connection connection = null;
	private Statement statement = null;
	private boolean initialized = false;

	/**
	 * 
	 * @param dBPath
	 */
	public Database(String dBPath) {
		DBPath = dBPath;
	}

	/**
	 * 
	 */
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
			statement = connection.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Connection error");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection error");
		}
	}

	/**
	 * 
	 */
	public void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet query(String query) {
		ResultSet result = null;

		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error in query : " + query);
		}

		return result;
	}

	/**
	 * 
	 * @param object
	 */
	public void addRow(Object object) {
		try {
			String string = "INSERT INTO " + object.getClass().getSimpleName()
					+ " Values(?";

			for (int i = 1; i < object.getClass().getDeclaredFields().length; i++) {
				string += ",?";
			}

			string += ")";

			PreparedStatement preparedStatement = connection
					.prepareStatement(string);

			Field field[] = object.getClass().getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				Object value = field[i].get(object);
				if (value != null) {
					if (field[i].getType().getSimpleName().equals("String")) {
						preparedStatement.setString(i + 1, (String) value);
					} else if (field[i].getType().getSimpleName().equals("int")) {
						preparedStatement.setInt(i + 1, (int) value);
					}
				}
			}

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void createTables() {
		if (!initialized) {
			System.out.println("Creating and initializing tables...");
			try {
				for (int i = 0; i < Query.CREATE_TABLES.length; i++) {
					statement.execute(Query.CREATE_TABLES[i]);
				}
				
				for (int i = 0; i < Query.CREATE_CSV_TABLES.length; i++) {
					statement.execute(Query.CREATE_CSV_TABLES[i]);
				}
				
				
				initialized = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}