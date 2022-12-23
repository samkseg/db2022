# db2022-inl
Inlämningsuppgift

## Entity Relationship Diagram
erDiagram
    Student ||--|{ StudentSchool : enrolls
    Student ||--|{ Hobby : has
    Student ||--|{ Phone : registers
    School ||--|{ StudentSchool : accepts
    

    Hobby {
    	int Hobbyid
	int StudentId
	string Category

    Phone {
    	int PhoneId
	int StudentId
	string Type
	string Number
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

