SELECT name 
FROM (
    SELECT Area_id, name, COUNT(Gender_id) as C1 
	FROM (
        (SELECT Gender_id, Area_id 
        FROM Artist) 
        NATURAL JOIN Area 
    )
	WHERE Gender_id = 'Male' AND Type_of_Area='City' 
	GROUP BY Area_id 
)
NATURAL JOIN (
    SELECT Area_id, name, COUNT(Gender_id) as C2 
	FROM (
        (SELECT Gender_id, Area_id 
        FROM Artist) 
        NATURAL JOIN Area 
    )
	WHERE Gender_id = 'Female' AND Type_of_Area='City' 
	GROUP BY Area_id 
)
WHERE C2 > C1 