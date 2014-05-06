package com.group09.database;


/**
 * 
 * @author Group 09
 * 
 */
public abstract class Query {
	
	/**
	 * 
	 */
	public static final String[] TABLES = {
		"CREATE TABLE Genre (Genre_id INTEGER, Name CHAR (50), Count INTEGER, PRIMARY KEY (Genre_id)) ",
		"CREATE TABLE GENDER (ID INT PRIMARY KEY NOT NULL, NAME CHAR(50))",
	};

	/**
	 * 
	 */
	public static final String[] QUERIES = {
		"SELECT * FROM GENRE",
		"SELECT * FROM GENDER",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
	};

	/**
	 * 
	 */
	public static final String TABLE_NAMES[] = {
		"Type",
		"Gender",
		"Genre",
		"Area",
		"Artist",
		"Recording",
		"has_recorded",
		"Medium",
		"Release",
		"is_track_on"
	};
}
