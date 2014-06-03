SELECT Name 
FROM( 
    SELECT r.Name, r.Release_id, COUNT(t.Recording_id) as tracks 
    FROM Release r, is_released i, Medium m, is_track_on t 
    WHERE r.Release_id = i.Release_id AND i.Medium_id = m.Medium_id AND m.Medium_id = t.Medium_id 
    GROUP BY r.Release_id 
)
ORDER BY tracks LIMIT 1 