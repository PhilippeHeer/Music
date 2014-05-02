package com.group09.main;

import com.group09.database.Database;
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
		Database database = new Database("Database.db");
		database.connect();

		new Window(database);
		
//		database.createTables();
//		
//		Genre genre = new Genre(46, 23, "Rock");
//		Gender gender = new Male(45, "genderName???");
//		database.addGenre(genre);
//		database.addGender(gender);
        

		

		
//		database.close();
	}
}
