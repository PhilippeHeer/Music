package com.group09.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group09.entities.Gender;
import com.group09.entities.Genre;

/**
 * 
 * @author Group 09
 * 
 */
public class Database {
	private String DBPath = "";
	private Connection connection = null;
	private Statement statement = null;

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
	 * @param genre
	 */
	public void addGenre(Genre genre) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO GENRE VALUES(?,?,?)");
			preparedStatement.setInt(1, genre.getID());
			preparedStatement.setInt(2, genre.getCount());
			preparedStatement.setString(3, genre.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param gender
	 */
	public void addGender(Gender gender) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO GENDER VALUES(?,?)");
			preparedStatement.setInt(1, gender.getID());
			preparedStatement.setString(2, gender.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param data
	 * @param index
	 */
	public void addRow(String[] data, int index) {
		String string = "INSERT INTO " + Query.TABLE_NAMES[index] + " VALUES(?";

		for (int i = 1; i < data.length; i++) {
			string += ",?";
		}

		string += ")";

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(string);

			for (int i = 0; i < data.length; i++) {
				preparedStatement.setString(i + 1, data[i]);
			}

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void createTables() {
		try {
			for (int i = 0; i < Query.TABLES.length; i++) {
				statement.executeUpdate(Query.TABLES[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}