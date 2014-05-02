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
	 */
	public void createTables() {
		String sql1 = "CREATE TABLE GENRE "
				+ "(ID INT PRIMARY KEY     NOT NULL,"
				+ " COUNT            INT     NOT NULL, "
				+ " NAME       CHAR(50)) ";
		String sql2 = "CREATE TABLE GENDER "
				+ "(ID INT PRIMARY KEY     NOT NULL,"
				+ " NAME       CHAR(50)) ";
		try {
			statement.executeUpdate(sql1);
			statement.executeUpdate(sql2);
		} catch (SQLException e) {
			// mostly because the tables already exist
			e.printStackTrace();
		}
	}
}