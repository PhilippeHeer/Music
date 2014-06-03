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
}
