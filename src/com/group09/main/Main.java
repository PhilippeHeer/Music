package com.group09.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.group09.database.Database;
import com.group09.entities.Gender;
import com.group09.entities.Genre;
import com.group09.entities.Male;
import com.group09.gui.Window;

/**
 * 
 * @author Group 09
 * 
 */
public class Main {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Window();
		Database database = new Database("Database.db");
		database.connect();
		
//		database.createTables();
		
//		Genre genre = new Genre(46, 23, "Rock");
//		Gender gender = new Male(45, "genderName???");
//		database.addGenre(genre);
//		database.addGender(gender);
        

		ResultSet resultSet = database.query("SELECT * FROM GENRE");
		try {
			while (resultSet.next()) {
				System.out.println("Name : " + resultSet.getString("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		database.close();
	}
}
