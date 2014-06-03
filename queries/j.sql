SELECT Name 
FROM(
    SELECT r.Name, COUNT( m.Medium_id ) med 
    FROM has_recorded h, Recording r, is_track_on t, Medium m, (
        SELECT a.Artist_id 
        FROM Artist a 
        WHERE a.Name = 'Metallica' 
    ) a 
    WHERE a.Artist_id = h.Artist_id AND h.Recording_id = t.Recording_id AND t.Medium_id = m.Medium_id 
    GROUP BY r.Recording_id 
) 
ORDER BY med LIMIT 25 