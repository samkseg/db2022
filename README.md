# db2022-inl
Assignment for course in Database development

## Entity Relationship Diagram

```mermaid
erDiagram
    Student ||--o{ StudentSchool : enrolls
    School ||--o{ StudentSchool : accepts
    Student ||--o{ StudentHobby : has
    Hobby ||--o{ StudentHobby : involves
    Student ||--o{ Phone : has
    Student }|--o| Grade : has
       

    Hobby {
    	int Hobbyid
	string Name
    }

    Phone {
    	int PhoneId
	int StudentId
	string Type
	string Number
    }

    StudentHobby {
	  int StudentId
	  int HobbyId 
    }

    StudentSchool {
        int StudentId
        int SchoolId
    }

    Student {
        int StudentId
        string FirstName
        string LastName
	int GradeId
    }

    School {
        int SchoolId
        string Name
        string City
    }

    Grade {
        int GradeId
        string Name
    }
```

## Cardinality

![Cardinality](cardinality-1.png)

## Clone repository
1. cd ~
2. mkdir ws
3. cd ws
4. git clone https://github.com/samkseg/db2022-inl
5. cd db2022-inl

## Instructions for ORM application
1. Run MYSQL server named iths-mysql in Docker container
2. docker exec -i iths-mysql mysql -uroot -proot < db_auto.sql
3. docker exec -i iths-mysql mysql -uroot -proot <<< "GRANT ALL PRIVILEGES ON \*.\* to 'iths'@'%';"
4. gradle run

## Instructions for normalizing script
1. Run MYSQL in Docker container
2. docker cp denormalized-data.csv iths-mysql:/var/lib/mysql-files
3. docker exec -i iths-mysql mysql -uroot -prootlisering.sql < normalisering.sql
