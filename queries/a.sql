SELECT Name 
FROM ( 
    SELECT Area_id 
    FROM Area 
    WHERE Name = 'Switzerland' 
) 
NATURAL JOIN Artist