/* Type */
INSERT INTO Type (Type_id, Name) VALUES (0, 'Person')
INSERT INTO Type (Type_id, Name) VALUES (1, 'Groupe')

/* Gender */
INSERT INTO Gender (Gender_id, Name) VALUES (0, 'Male')
INSERT INTO Gender (Gender_id, Name) VALUES (1, 'Female')
INSERT INTO Gender (Gender_id, Name) VALUES (2, 'Ohter')

/* Genre */
INSERT INTO Genre (Genre_id, Name, Count) SELECT * FROM Genre_csv

/* Area */        
INSERT INTO Area (Area_id, Name, Type_of_area) SELECT * FROM Area_csv

/* Artist */
INSERT INTO Artist (Artist_id, Name, Type_id, Gender_id, Area_id) SELECT * FROM Artist_csv

/* is_genre */
INSERT INTO is_genre (Artist_id, Genre_id) SELECT *  FROM Artist_genre_csv

/* Recording */
INSERT INTO Recording (Recording_id, Name, Length) SELECT * FROM Recording_csv

/* has_recorded */
INSERT INTO has_recorded (Artist_id, Recording_id) SELECT DISTINCT ArtistID, TrackID FROM artist_track_csv NATURAL JOIN Track_csv

/* Medium */
INSERT INTO Medium (Medium_id, Format) SELECT ID, Format FROM Medium_csv

/* Release */
INSERT INTO Release (Release_id, Name) SELECT * FROM Release_csv

/* is_track_on */
INSERT INTO is_track_on (Recording_id, Medium_id, Position) SELECT RecordingID, MediumID, Position FROM Track_csv

/* is_released */
INSERT INTO is_released (Medium_id, Release_id) SELECT ID, ReleaseID FROM Medium_csv