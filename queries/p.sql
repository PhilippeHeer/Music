SELECT Name 
FROM Release 
WHERE Release_id IN (
    SELECT Release_id 
    FROM(
        SELECT i.Release_id, COUNT(i.Medium_id) as med1 
        FROM is_released i, Release r, Medium m 
        WHERE i.Release_id = r.Release_id AND m.Medium_id = i.Medium_id 
        GROUP BY i.Release_id 
    )
    WHERE med1 IN (
        SELECT MAX(med2) 
        FROM( 
            SELECT i.Release_id, COUNT(i.Medium_id) as med2 
            FROM is_released i, Release r, Medium m 
            WHERE i.Release_id = r.Release_id AND m.Medium_id = i.Medium_id 
            GROUP BY i.Release_id 
        )
    )
)