SELECT Name 
FROM ( 
    SELECT a.Name, COUNT( i.Medium_id ) AS Records 
	FROM has_recorded h, Artist a, Recording r, is_track_on i 
	WHERE a.Type_id = 'Group' AND a.Artist_id = h.Artist_id AND h.Recording_id = r.Recording_id AND r.Recording_id = i.Recording_id 
	GROUP BY a.Artist_id 
)
ORDER BY Records DESC LIMIT 10 