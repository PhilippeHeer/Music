CREATE TABLE Type (
        Type_id INTEGER,
        Name CHAR (10),
        PRIMARY KEY (Type_id)
)

CREATE TABLE Gender (
        Gender_id INTEGER,
        Name CHAR (10),
        PRIMARY KEY (Gender_id)
)

CREATE TABLE Genre (
        Genre_id INTEGER,
        Name CHAR (50),
        Count INTEGER,
        PRIMARY KEY (Genre_id)
)

CREATE TABLE Area (
        Area_id INTEGER,
        Name CHAR (100),
        Type_of_area CHAR (100),
        PRIMARY KEY (Area_id)
)

CREATE TABLE Artist (
        Artist_id INTEGER,
        Name CHAR (30),
        Type_id INTEGER,
        Gender_id INTEGER,
        Area_id INTEGER NOT NULL,
        PRIMARY KEY (Artist_id),
        FOREIGN KEY (Type_id) REFERENCES Type,
        FOREIGN KEY (Gender_id) REFERENCES Gender,
        FOREIGN KEY (Area_id) REFERENCES Area
)

CREATE TABLE is_genre (
        Artist_id INTEGER,
        Genre_id INTEGER,
        PRIMARY KEY (Artist_id, Genre_id),
        FOREIGN KEY (Artist_id) REFERENCES Artist,
        FOREIGN KEY (Genre_id) REFERENCES Genre
)

CREATE TABLE Recording (
        Recording_id INTEGER,
        Name CHAR (100),
        Length INTEGER,
        PRIMARY KEY (Recording_id)
)

CREATE TABLE has_recorded (
        Artist_id INTEGER, /* implicit NOT NULL (used in primary key) */
        Recording_id INTEGER, /* implicit NOT NULL (idem) */
        PRIMARY KEY (Artist_id, Recording_id),
        FOREIGN KEY (Artist_id) REFERENCES Artist,
        FOREIGN KEY (Recording_id) REFERENCES Recording
)

CREATE TABLE Medium (
        Medium_id INTEGER,
        Format CHAR (30),
        PRIMARY KEY (Medium_id)
)

CREATE TABLE Release (
        Release_id INTEGER,
        Name CHAR (200),
        PRIMARY KEY (Release_id)
)

CREATE TABLE is_track_on (
        Recording_id INTEGER,
        Medium_id INTEGER,
        Position INTEGER,
        PRIMARY KEY (Recording_id, Medium_id, Position),
        FOREIGN KEY (Recording_id) REFERENCES Recording,
        FOREIGN KEY (Medium_id) REFERENCES Medium
)

CREATE TABLE is_released (
        Medium_id INTEGER,
        Release_id INTEGER,
        PRIMARY KEY (Medium_id, Release_id),
        FOREIGN KEY (Medium_id) REFERENCES Medium,
        FOREIGN KEY (Release_id) REFERENCES Release
)