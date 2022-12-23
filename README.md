# db2022-inl
Inlämningsuppgift

## Entity Relationship Diagram

```mermaid
erDiagram
    Student ||--|{ StudentSchool : enrolls
    School ||--|{ StudentSchool : accepts
    Student ||--|{ StudentHobby : has
    Hobby ||--|{ StudentHobby : of
    Student ||--|{ Phone : owns
       

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
	  string Category 
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
