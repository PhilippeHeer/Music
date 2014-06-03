SELECT Name 
FROM (
    SELECT a.Name, COUNT(g.Genre_id) as c 
	FROM Artist a, is_genre i, Genre g 
	WHERE a.Gender_id = 'Female' AND a.Artist_id = i.Artist_id AND i.Genre_id = g.Genre_id 
	GROUP BY a.Artist_id 
) 
ORDER BY c DESC LIMIT 1 