SELECT Name 
FROM ( 
    SELECT a.Name, COUNT( DISTINCT r1.Release_id ) AS Releases 
    FROM Release r1, is_released ir, is_track_on t, has_recorded h, Artist a, Medium m, Recording r2 
    WHERE a.Type_id = 'Group' AND a.Artist_id = h.Artist_id AND h.Recording_id = r2.Recording_id AND r2.Recording_id = t.Recording_id AND t.Medium_id = m.Medium_id AND m.Medium_id = ir.Medium_id AND ir.Release_id = r1.Release_id  
    GROUP BY a.Artist_id 
)
ORDER BY Releases DESC LIMIT 10 