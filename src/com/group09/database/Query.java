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

	/**
	 * 
	 */
	public static final String[] QUERIES = {
		//A OK
		"SELECT Name " +
		"FROM ( SELECT Area_id " +
				"FROM Area " +
				"WHERE Name = 'Switzerland') NATURAL JOIN Artist",
		
				
				
		//B TODO				
		"SELECT * FROM Type",
		/*SELECT a1.Name, male, female, groups
		FROM Area a1, (
		    SELECT *
		    FROM(
		        SELECT Area_id, male, female, groups
		        FROM (
		            SELECT a1.Area_id, 
		                COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
		                COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
		                COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
		            FROM Area a1, Artist a2 
		            WHERE a1.Area_id = a2.Area_id 
		            GROUP BY a1.Area_id 
		        )
		        WHERE male IN (
		            SELECT MAX(male)
		            FROM(
		                SELECT a1.Area_id, 
		                    COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
		                    COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
		                    COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
		                FROM Area a1, Artist a2 
		                WHERE a1.Area_id = a2.Area_id 
		                GROUP BY a1.Area_id 
		            )
		        )
		        LIMIT 1 
		    )
		    UNION ALL
		    SELECT *
		    FROM(
		        SELECT Area_id, male, female, groups
		        FROM (
		            SELECT a1.Area_id, 
		                COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
		                COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
		                COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
		            FROM Area a1, Artist a2 
		            WHERE a1.Area_id = a2.Area_id 
		            GROUP BY a1.Area_id 
		        )
		        WHERE female IN (
		            SELECT MAX(female)
		            FROM(
		                SELECT a1.Area_id, 
		                    COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
		                    COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
		                    COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
		                FROM Area a1, Artist a2 
		                WHERE a1.Area_id = a2.Area_id 
		                GROUP BY a1.Area_id 
		            )
		        )
		        LIMIT 1 
		    )
		    UNION ALL
		    SELECT *
		    FROM(
		        SELECT Area_id, male, female, groups
		        FROM (
		            SELECT a1.Area_id, 
		                COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
		                COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
		                COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
		            FROM Area a1, Artist a2 
		            WHERE a1.Area_id = a2.Area_id 
		            GROUP BY a1.Area_id 
		        )
		        WHERE groups IN (
		            SELECT MAX(groups)
		            FROM(
		                SELECT a1.Area_id, 
		                    COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
		                    COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
		                    COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
		                FROM Area a1, Artist a2 
		                WHERE a1.Area_id = a2.Area_id 
		                GROUP BY a1.Area_id 
		            )
		        )
		        LIMIT 1 
		    )
		) a2
		WHERE a1.Area_id = a2.Area_id*/
		
		
		
		//C has_recorded is missing
		"SELECT * " +
		"FROM ( SELECT h.Artist_id, COUNT(Recording_id) AS c " +
				"FROM has_recorded h, Artist a " +
				"WHERE a.Type_id = '1' AND a.Artist_id = h.Artist_id " +
				"GROUP BY a.Artist_id ) " +
		"ORDER BY c DESC LIMIT 10",
		
		
		
		//D has_recorded is missing
		"SELECT Name " +
		"FROM ( SELECT a.Artist_id, COUNT(t.Recording_id) AS c " +
				"FROM Release r1, is_released r2, is_track_on t, has_recorded h, Artist a " +
				"WHERE r1.Release_id = r2.Release_id AND r2.Medium_id = t.Medium_id AND t.Recording_id = h.Recording_id AND h.Artist_id = a.Artist_id " +
				"GROUP BY a.Artist_id ) " +
		"ORDER BY c DESC LIMIT 10",		
		
		
		
		//E
		"SELECT Name " +
		"FROM (SELECT a.Name, COUNT(i.Genre_id) as c, a.Gender_id, a.Type_id " +
				"FROM Artist a, is_genre i " +
				"WHERE a.Artist_id = i.Artist_id AND a.Gender_id = 'Female' AND a.Type_id = 'Person' " +
				"GROUP BY a.Artist_id) " +
		"ORDER BY c DESC LIMIT 1",
			
		
				
		//F
		"SELECT name " +
		"FROM (SELECT Area_id, name, COUNT(Gender_id) as C1 " +
				"FROM ((SELECT Gender_id, Area_id FROM Artist) NATURAL JOIN Area) " +
				"WHERE Gender_id = 'Male' AND Type_of_Area='City' " +
				"GROUP BY Area_id) " +
				"NATURAL JOIN (SELECT Area_id, name, COUNT(Gender_id) as C2 " +
								"FROM ((SELECT Gender_id, Area_id FROM Artist) NATURAL JOIN Area) " +
								"WHERE Gender_id = 'Female' AND Type_of_Area='City' " +
								"GROUP BY Area_id) " +
		"WHERE C2 > C1",
		
		
		
		//G
		"SELECT Release_id " +
		"FROM Release " +
		"WHERE Release_id IN ( " +
		    "SELECT Release_id " +
		    "FROM ( " +
				"SELECT r.Release_id, COUNT(i2.Recording_id) AS a " +
				"FROM  Release r, is_released i1, is_track_on i2 " +
				"WHERE  r.Release_id = i1.Release_id AND i1.Medium_id = i2.Medium_id " +
				"GROUP BY r.Release_id ) " +
			"WHERE a IN ( " +
				"SELECT MAX(b) " +
				"FROM ( " +
					"SELECT r.Release_id, COUNT(i2.Recording_id) AS b " +
					"FROM  Release r, is_released i1, is_track_on i2 " +
					"WHERE  r.Release_id = i1.Release_id AND i1.Medium_id = i2.Medium_id " +
					"GROUP BY r.Release_id ) " +
			") " +
		") ",
		
						

		//H missing the part with the most tracks recorded
		"SELECT Name " +
		"FROM (SELECT Area_id " +
				"FROM (SELECT Area_id, COUNT(Artist_id) as c " +
						"FROM ( SELECT a.Area_id, a.Artist_id " +
								"FROM Artist a " +
								"WHERE a.Type_id = 'Person' ) NATURAL JOIN Area " +
						"GROUP BY Area_id) " +
				"WHERE c > 30) NATURAL JOIN Artist " +
		"WHERE Gender_id = 'Male' OR Gender_id = 'Female' ",
		
		
		
		//I TODO
		"SELECT * FROM Type",
		
		
		
		//J TODO
		"SELECT * FROM Type",
		
		
		
		//K TODO
		"SELECT * FROM Type",
		
		
		
		//L
		"SELECT Name " +
		"FROM Genre " +
		"WHERE Genre_id NOT IN " + 
		"(SELECT Genre_id " +
		"FROM ( SELECT Genre_id, Gender_id, Type_id FROM ( ( SELECT Artist_id, Gender_id, Type_id FROM Artist ) NATURAL JOIN is_genre ) NATURAL JOIN Genre ) " +
		"WHERE Gender_id = 'Male') " +
		"UNION " + 
		"SELECT Name " + 
		"FROM Genre " +
		"WHERE Genre_id NOT IN " + 
		"(SELECT Genre_id " +
		"FROM ( SELECT Genre_id, Gender_id, Type_id FROM ( ( SELECT Artist_id, Gender_id, Type_id FROM Artist ) NATURAL JOIN is_genre ) NATURAL JOIN Genre ) " +
		"WHERE Gender_id = 'Female' ) " +
		"UNION " +
		"SELECT Name " + 
		"FROM Genre " +
		"WHERE Genre_id NOT IN " + 
		"(SELECT Genre_id " +
		"FROM ( SELECT Genre_id, Gender_id, Type_id FROM ( ( SELECT Artist_id, Gender_id, Type_id FROM Artist ) NATURAL JOIN is_genre ) NATURAL JOIN Genre ) " +
		"WHERE Type_id = 'Group') ",
		
		
		
		//M missing the part with the highest number of tracks
		"SELECT Name " +
		"FROM (SELECT Area_id " +
				"FROM (SELECT Area_id, COUNT(Artist_id) as c " +
						"FROM ( SELECT a.Area_id, a.Artist_id " +
								"FROM Artist a " +
								"WHERE a.Type_id = 'Group' ) NATURAL JOIN Area " +
						"GROUP BY Area_id) " +
				"WHERE c > 10) NATURAL JOIN Artist " +
		"WHERE Gender_id = 'Male' " +
		"LIMIT 5",
		
		
		
		//N TODO
		"SELECT * FROM Type",
		
		
		
		//O TODO
		"SELECT * FROM Type",
		
		
		
		//P TODO
		"SELECT * FROM Type",
		
		
		
		//Q
		"SELECT Name " +
		"FROM Genre " +
		"WHERE Genre_id IN ( " +
		    "SELECT Genre_id " +
		    "FROM ( " +
		        "SELECT g.Genre_id, COUNT(a.Artist_id) as art1 " +
		        "FROM Artist a, is_genre i, Genre g " + 
		        "WHERE a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id AND a.Artist_id IN ( " + 
		            "SELECT Artist_id " +
		                "FROM ( " +
		                "SELECT a.Artist_id, COUNT(g.Genre_id) as gen " + 
		                "FROM Artist a, is_genre i, Genre g " +
		                "WHERE a.Type_id = 'Group' AND a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id " + 
		                "GROUP BY a.Artist_id " +
		            ") " +
		            "WHERE gen > 2 " +
		        ") " +
		        "GROUP BY g.Genre_id " + 
		    ") " +
		    "WHERE art1 IN ( " +
		        "SELECT MAX(art2) " +
		        "FROM( " +
		        "SELECT g.Genre_id, COUNT(a.Artist_id) as art2 " +
		        "FROM Artist a, is_genre i, Genre g " +
		        "WHERE a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id AND a.Artist_id IN ( " + 
		            "SELECT Artist_id " +
		            "FROM ( " +
		                    "SELECT a.Artist_id, COUNT(g.Genre_id) as gen " + 
		                    "FROM Artist a, is_genre i, Genre g " +
		                    "WHERE a.Type_id = 'Group' AND a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id " + 
		                    "GROUP BY a.Artist_id " +
		                ") " +
		                "WHERE gen > 2 " +
		            ") " +
		            "GROUP BY g.Genre_id " + 
		        ") " +
		    ") " +
		") ",		
		
		
		//R TODO
		"SELECT * FROM Type",
		
		
		
		//S TODO
		"SELECT * FROM Type",
		
		
		
		//T TODO
		"SELECT * FROM Type",
	};

	/**
	 * 
	 */
	public static final String[] FILL_TABLES = {
		//0 Type
		"INSERT INTO Type (Type_id, Name)"
				+ "VALUES (0, 'Person')",
		"INSERT INTO Type (Type_id, Name)"
				+ "VALUES (1, 'Groupe')",
		
		//2 Gender
		"INSERT INTO Gender (Gender_id, Name)"
				+ "VALUES (0, 'Male')",
		"INSERT INTO Gender (Gender_id, Name)"
				+ "VALUES (1, 'Female')",
		"INSERT INTO Gender (Gender_id, Name)"
				+ "VALUES (2, 'Ohter')",

		//5 Genre
		"INSERT INTO Genre (Genre_id, Name, Count) SELECT * FROM Genre_csv",
				
		//6 Area
		"INSERT INTO Area (Area_id, Name, Type_of_area) SELECT * FROM Area_csv",
		
		//7 Artist
		"INSERT INTO Artist (Artist_id, Name, Type_id, Gender_id, Area_id) SELECT * FROM Artist_csv",

		//8 is_genre
		"INSERT INTO is_genre (Artist_id, Genre_id) SELECT *  FROM Artist_genre_csv",
		
		//9 Recording
		"INSERT INTO Recording (Recording_id, Name, Length) SELECT * FROM Recording_csv",

		//10 has_recorded
		"INSERT INTO has_recorded (Artist_id, Recording_id) SELECT a.ArtistID, t.RecordingID FROM Artist_track_csv a, Track_csv t WHERE a.TrackID = t.ID",

		//11 Medium
		"INSERT INTO Medium (Medium_id, Format) SELECT ID, Format FROM Medium_csv",

		//12 Release
		"INSERT INTO Release (Release_id, Name) SELECT * FROM Release_csv",

		//13 is_track_on
		"INSERT INTO is_track_on (Recording_id, Medium_id, Position) SELECT RecordingID, MediumID, Position FROM Track_csv",

		//14 is_released
		"INSERT INTO is_released (Medium_id, Release_id) SELECT ID, ReleaseID FROM Medium_csv"
	};
}
