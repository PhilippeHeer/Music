package com.group09.database;


/**
 * 
 * @author Philippe
 * 
 */
public abstract class Query {

	/**
	 * 
	 */
	public static final String[] CREATE_TABLES = {		
		"CREATE TABLE Type (" +
				"Type_id INTEGER, " +
				"Name CHAR (10), " +
				"PRIMARY KEY (Type_id))",		

		"CREATE TABLE Gender (" +
				"Gender_id INTEGER, " +
				"Name CHAR (10), " +
				"PRIMARY KEY (Gender_id))",		

		"CREATE TABLE Genre (" +
				"Genre_id INTEGER, " +
				"Name CHAR (50), " +
				"Count INTEGER, " +
				"PRIMARY KEY (Genre_id))",

		"CREATE TABLE Area (" +
				"Area_id INTEGER, " +
				"Name CHAR (100), " +
				"Type_of_area CHAR (100), " +
				"PRIMARY KEY (Area_id))",

		"CREATE TABLE Artist (" +
				"Artist_id INTEGER, " +
				"Name CHAR (30), " +
				"Type_id INTEGER, " +
				"Gender_id INTEGER,	" +
				"Area_id INTEGER NOT NULL, " +
				"PRIMARY KEY (Artist_id), " +
				"FOREIGN KEY (Type_id) REFERENCES Type, " +
				"FOREIGN KEY (Gender_id) REFERENCES Gender, " +
				"FOREIGN KEY (Area_id) REFERENCES Area)",

		"CREATE TABLE is_genre (" +
				"Artist_id INTEGER, " +
				"Genre_id INTEGER, " +
				"PRIMARY KEY (Artist_id, Genre_id), " +
				"FOREIGN KEY (Artist_id) REFERENCES Artist, " +
				"FOREIGN KEY (Genre_id) REFERENCES Genre)",

		"CREATE TABLE Recording (" +
				"Recording_id INTEGER, " +
				"Name CHAR (100), " +
				"Length INTEGER, " +
				"PRIMARY KEY (Recording_id))",

		"CREATE TABLE has_recorded (" +
				"Artist_id INTEGER, " + // implicit NOT NULL (used in primary key)
				"Recording_id INTEGER, " + // implicit NOT NULL (idem)
				"PRIMARY KEY (Artist_id, Recording_id), " +
				"FOREIGN KEY (Artist_id) REFERENCES Artist, " +
				"FOREIGN KEY (Recording_id) REFERENCES Recording)",

		"CREATE TABLE Medium (" +
				"Medium_id INTEGER, " +
				"Format CHAR (30), " +
				"PRIMARY KEY (Medium_id))",

		"CREATE TABLE Release (" +
				"Release_id INTEGER, " +
				"Name CHAR (200), " +
				"PRIMARY KEY (Release_id))",

		"CREATE TABLE is_track_on (" +
				"Recording_id INTEGER, " +
				"Medium_id INTEGER, " +
				"Position INTEGER, " +
				"PRIMARY KEY (Recording_id, Medium_id, Position), " +
				"FOREIGN KEY (Recording_id) REFERENCES Recording, " +
				"FOREIGN KEY (Medium_id) REFERENCES Medium)",

		"CREATE TABLE is_released (" +
				"Medium_id INTEGER, " +
				"Release_id INTEGER, " +
				"PRIMARY KEY (Medium_id, Release_id), " +
				"FOREIGN KEY (Medium_id) REFERENCES Medium, " +
				"FOREIGN KEY (Release_id) REFERENCES Release)",
	};

	public static final String[] NAMES_CSV_TABLES = {	
		"Area_csv",
		"Artist_csv",
		"Artist_genre_csv",
		"Artist_track_csv",
		"Genre_csv",
		"Medium_csv",
		"Recording_csv",
		"Release_csv",
		"Track_csv",
	};

	/**
	 * 
	 */
	public static final String[] QUERIES = {
		"SELECT * FROM Type",
		"SELECT * FROM Gender",
		"SELECT * FROM Genre",
		"SELECT * FROM Area",
		"SELECT * FROM Artist",
		"SELECT * FROM is_genre",
		"SELECT * FROM Recording",
		"SELECT * FROM has_recorded",
		"SELECT * FROM Medium",
		"SELECT * FROM Release",
		"SELECT * FROM is_track_on",
		"SELECT * FROM is_released",
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
		"is_genre",
		"Recording",
		"has_recorded",
		"Medium",
		"Release",
		"is_track_on",
		"is_released",
	};

	public static final String[] FILL_TABLES = {
		//Type
//		"INSERT INTO Type (Type_id, Name)"
//				+ "VALUES (0, 'Person')",
//		"INSERT INTO Type (Type_id, Name)"
//				+ "VALUES (1, 'Groupe')",
		
		//Gender
//		"INSERT INTO Gender (Gender_id, Name)"
//				+ "VALUES (0, 'Male')",
//		"INSERT INTO Gender (Gender_id, Name)"
//				+ "VALUES (1, 'Female')",
//		"INSERT INTO Gender (Gender_id, Name)"
//				+ "VALUES (2, 'Ohter')",

		//Genre
		"INSERT INTO Genre (Genre_id, Name, Count) SELECT * FROM Genre_csv",
				
		//Area
		"INSERT INTO Area (Area_id, Name, Type_of_area) SELECT * FROM Area_csv",
		
		//Artist
		"INSERT INTO Artist (Artist_id, Name, Type_id, Gender_id, Area_id) SELECT * FROM Artist_csv",

		//is_genre
		"INSERT INTO is_genre (Artist_id, Genre_id) SELECT *  FROM Artist_genre_csv",
		
		//Recording
		"INSERT INTO Recording (Recording_id, Name, Length) SELECT * FROM Recording_csv",

		//has_recorded
		"INSERT INTO has_recorded (Artist_id, Recording_id) SELECT a.ArtistID, t.RecordingID FROM Artist_track_csv a, Track_csv t WHERE a.TrackID = t.ID",

		//Medium
		"INSERT INTO Medium (Medium_id, Format) SELECT ID, Format FROM Medium_csv",

		//Release
		"INSERT INTO Release (Release_id, Name) SELECT * FROM Release_csv",

		//is_track_on
		"INSERT INTO is_track_on (Recording_id, Medium_id, Position) SELECT RecordingID, MediumID, Position FROM Track_csv",

		//is_released
		"INSERT INTO is_released (Medium_id, Release_id) SELECT ID, ReleaseID FROM Medium_csv"
	};

}
