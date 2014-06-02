package com.group09.main;

import com.group09.database.Database;
import com.group09.gui.Window;

/**
 * 
 * @author Philippe
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

		database.createTables();

		new Window(database);
	}
}
