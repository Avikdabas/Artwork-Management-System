# Artwork Management 2024

## Requirements
- Java JDK 8+
- MySQL Server

## Setup Instructions

1. Import the SQL file into MySQL:
   - Run the SQL in `database/artwork_db.sql` to create the database and table.

2. Update DB credentials in `DBHelper.java` if needed.

3. Compile and run:
   - `javac *.java`
   - `java ArtworkManagementApp`

## Features
- Add new artwork (title, artist, price)
- View all artworks
- Delete artwork