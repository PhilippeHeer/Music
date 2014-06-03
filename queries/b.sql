SELECT a1.Name, male, female, groups
FROM Area a1, (
    SELECT *
    FROM(
        SELECT Area_id, male, female, groups
        FROM (
            SELECT a1.Area_id, 
                COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
                COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
                COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
            FROM Area a1, Artist a2 
            WHERE a1.Area_id = a2.Area_id 
            GROUP BY a1.Area_id 
        )
        WHERE male IN (
            SELECT MAX(male)
            FROM(
                SELECT a1.Area_id, 
                    COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
                    COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
                    COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
                FROM Area a1, Artist a2 
                WHERE a1.Area_id = a2.Area_id 
                GROUP BY a1.Area_id 
            )
        )
        LIMIT 1 
    )
    UNION ALL
    SELECT *
    FROM(
        SELECT Area_id, male, female, groups
        FROM (
            SELECT a1.Area_id, 
                COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
                COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
                COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
            FROM Area a1, Artist a2 
            WHERE a1.Area_id = a2.Area_id 
            GROUP BY a1.Area_id 
        )
        WHERE female IN (
            SELECT MAX(female)
            FROM(
                SELECT a1.Area_id, 
                    COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
                    COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
                    COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
                FROM Area a1, Artist a2 
                WHERE a1.Area_id = a2.Area_id 
                GROUP BY a1.Area_id 
            )
        )
        LIMIT 1 
    )
    UNION ALL
    SELECT *
    FROM(
        SELECT Area_id, male, female, groups
        FROM (
            SELECT a1.Area_id, 
                COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
                COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
                COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
            FROM Area a1, Artist a2 
            WHERE a1.Area_id = a2.Area_id 
            GROUP BY a1.Area_id 
        )
        WHERE groups IN (
            SELECT MAX(groups)
            FROM(
                SELECT a1.Area_id, 
                    COUNT(CASE WHEN a2.Gender_id = 'Male' THEN 1 ELSE NULL END) AS male, 
                    COUNT(CASE WHEN a2.Gender_id = 'Female' THEN 1 ELSE NULL END) AS female,
                    COUNT(CASE WHEN a2.Type_id = 'Group' THEN 1 ELSE NULL END) AS groups 
                FROM Area a1, Artist a2 
                WHERE a1.Area_id = a2.Area_id 
                GROUP BY a1.Area_id 
            )
        )
        LIMIT 1 
    )
) a2
WHERE a1.Area_id = a2.Area_id