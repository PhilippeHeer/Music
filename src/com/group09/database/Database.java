package com.group09.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 * @author Philippe
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
	 * @param object
	 */
	public void deleteRow(Object object) {
		String string = "";
		try {
			string = "DELETE FROM " + object.getClass().getSimpleName()
					+ " WHERE ";
			
			int number = 0;

			Field field[] = object.getClass().getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				Object value = field[i].get(object);
				if (value != null) {
					if (field[i].getType().getSimpleName().equals("String")) {
						if( !((String) value).equals("") ){
							number++;
						}
					} else if (field[i].getType().getSimpleName().equals("int")) {
						if( (int)value != -1 ){
							number++;
						}
					}
				}
			}

			int j = 1;
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				Object value = field[i].get(object);
				if (value != null) {
					String substring = field[i].getName() + " = ";
					if (field[i].getType().getSimpleName().equals("String")) {
						if( !((String) value).equals("") ){
							substring += "'" + (String)value + "'";
							string += substring;
							if(j!=number){
								string += " AND ";
							}
							j++;
						}
					} else if (field[i].getType().getSimpleName().equals("int")) {
						if( (int)value != -1 ){
							substring += value.toString();
							string += substring;
							if(j!=number){
								string += " AND ";
							}
							j++;
						}
					}
				}
			}
			
			if( number > 0 ){
				statement.executeQuery(string);
			}
			
		} catch (SQLException e) {
			System.out.println(string);
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

		// 1. Simple tables creation (our tables). Uncomment if tables are not created yet.
		String query = "";
		Scanner scanner;
		try {
			scanner = new Scanner(new File("createTables/createTables.sql"));
			while (scanner.hasNext()) {
				String readLine = scanner.nextLine();
				while (!readLine.equals("")) {
					query = query + readLine;
					readLine = scanner.nextLine();
				}
				System.out.println("Table created : \n" + query);

				if (!((query.equals("")) || (query.charAt(0) == '/'))) {
					statement.execute(query);
				}
				query = "";
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Tables already created.");
		} catch (NoSuchElementException e) {
			
		}

		// 2. Translate CSV files into new tables using SQLITE DATABASE
		// BROWSER (done by user).

		// 3. Clean all datas to protect each special character

		// 4. Queries to import raw data from csv into our own tables
		try {
			scanner = new Scanner(new File("fillTables/fillTables.sql"));
			while (scanner.hasNext()) {
				query = scanner.nextLine();

				if (!((query.equals("")) || (query.charAt(0) == '/'))) {
					System.out.println("Executing " + query);
					statement.execute(query);
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Tables already filled.");
		}
	}
}
