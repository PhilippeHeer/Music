SELECT Name
FROM Genre
WHERE Genre_id IN (

    SELECT Genre_id 
    FROM (

        SELECT g.Genre_id, COUNT(a.Artist_id) as art1 
        FROM Artist a, is_genre i, Genre g 
        WHERE a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id AND a.Artist_id IN ( 
            SELECT Artist_id 
                FROM (
                SELECT a.Artist_id, COUNT(g.Genre_id) as gen 
                FROM Artist a, is_genre i, Genre g 
                WHERE a.Type_id = 'Group' AND a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id 
                GROUP BY a.Artist_id 
            )
            WHERE gen > 2 
        )
        GROUP BY g.Genre_id 
    )
    WHERE art1 IN (

        SELECT MAX(art2) 
        FROM(

        SELECT g.Genre_id, COUNT(a.Artist_id) as art2 
        FROM Artist a, is_genre i, Genre g 
        WHERE a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id AND a.Artist_id IN ( 
            SELECT Artist_id 
            FROM (
                    SELECT a.Artist_id, COUNT(g.Genre_id) as gen 
                    FROM Artist a, is_genre i, Genre g 
                    WHERE a.Type_id = 'Group' AND a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id  
                    GROUP BY a.Artist_id 
                )
                WHERE gen > 2 
            )
            GROUP BY g.Genre_id 
        )
    )
)