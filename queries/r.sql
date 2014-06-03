SELECT name, COUNT(Recording_id) as records 
FROM Recording 
GROUP BY name 
ORDER by records DESC LIMIT 5 