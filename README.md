# db2022-inl
Inlämningsuppgift

## Entity Relationship Diagram

```mermaid
erDiagram
    Student ||--|{ StudentSchool : enrolls
    School ||--|{ StudentSchool : accepts
    Student ||--|{ StudentHobby : practices
    Hobby ||--|{ StudentHobby : of
    Student ||--|{ Phone : registers
       

    Hobby {
    	int Hobbyid
	int StudentId
	string Category
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
    }

    School {
        int SchoolId
        string Name
        string City
    }
```

## Cardinality

![Cardinality](cardinality-1.png)
