SELECT Area_id , artists 
FROM( 
    SELECT a1.Area_id, COUNT(a2.Artist_id) as artists 
    FROM Area a1, Artist a2 
    WHERE a1.Area_id = a2.Area_id 
    GROUP BY a1.Area_id 
)
WHERE artists > 30 