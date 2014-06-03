SELECT Release_id 
FROM Release 
WHERE Release_id IN (
    SELECT Release_id 
    FROM (
        SELECT r.Release_id, COUNT(i2.Recording_id) AS a 
        FROM  Release r, is_released i1, is_track_on i2 
        WHERE  r.Release_id = i1.Release_id AND i1.Medium_id = i2.Medium_id 
        GROUP BY r.Release_id ) 
    WHERE a IN ( 
        SELECT MAX(b) 
        FROM ( 
            SELECT r.Release_id, COUNT(i2.Recording_id) AS b 
            FROM  Release r, is_released i1, is_track_on i2 
            WHERE  r.Release_id = i1.Release_id AND i1.Medium_id = i2.Medium_id 
            GROUP BY r.Release_id 
        )
    )
)