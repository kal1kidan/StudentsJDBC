# StudentsJDBC

**Lab Assignment 1: Enterprise Application Development**
**Topic:** Java Database Connectivity (JDBC) with MySQL

| | |
|---|---|
| **Name** | Kalkidan Asdesach |
| **ID** | UGR/5481/16 |
| **Stream** | Software Engineering, 4th Year |
| **Course** | Enterprise Application Development |
| **University** | Addis Ababa University |

---

## Description

This lab shows how a Java application talks to a relational database using JDBC. The program connects to a MySQL server, creates a database called `StudentsDB` with a `students` table, and then performs the basic CRUD operations (Create, Read, Update, Delete) plus an aggregate query, all from Java code.

Each task is a separate Java class and a separate Git commit, so the progress of the work can be followed in the commit history.

## Database schema

Database: `StudentsDB`

Table: `students`

| Column | Type | Notes |
|---|---|---|
| `id` | INT | Primary key |
| `firstname` | VARCHAR(255) | |
| `lastname` | VARCHAR(255) | |
| `grade` | INT | |

## Tasks

| Task | Class | Description |
|---|---|---|
| 1 | `Task1.java` | Connect to the MySQL server, create the `StudentsDB` database and the `students` table. |
| 2 | `Task2.java` | Insert one example row, then ten more rows, using a `PreparedStatement`. The table is cleared first so the program can be re-run. |
| 3 | `Task3.java` | Retrieve and display five rows from the `students` table (`SELECT * FROM students LIMIT 5`). |
| 4 | `Task4.java` | Update the `firstname` of a student with a given `id`. Prints the row before and after the update. |
| 5 | `Task5.java` | Delete a student with a given `id`. Prints the number of rows before and after. |
| 6 | `Task6.java` | Calculate and display the average grade of all students (`SELECT AVG(grade) ...`). |

`Main.java` is a simple connection test that prints `Established Connection`.

### Expected results

- **Task 1:** prints `Established Connection`, `Database StudentsDB ready.` and `Table students ready.`
- **Task 2:** the table contains 11 rows afterwards.
- **Task 3:** prints five students, starting with ID 1.
- **Task 4:** student 1 changes from `John` to `Johnny`.
- **Task 5:** student 2 is deleted, leaving 10 rows.
- **Task 6:** with the sample data, the average grade is `83.00`.

Tasks 2, 4 and 5 change the data, so run them in order (1 to 6) for the results above.

## Technologies

- Java 17 (Eclipse Temurin)
- Maven
- MySQL Server 8.0 and MySQL Workbench 8.0
- MySQL Connector/J 8.4.0
- IntelliJ IDEA Community Edition
- Git and GitHub

## Project structure

```
StudentsJDBC
├── pom.xml                      Maven configuration (includes the MySQL driver)
├── config.properties.example    Template for database settings
├── .gitignore
└── src/main/java/org/example
    ├── Main.java                Connection test
    ├── Task1.java               Create database and table
    ├── Task2.java               Insert data
    ├── Task3.java               Retrieve data
    ├── Task4.java               Update data
    ├── Task5.java               Delete data
    └── Task6.java               Average grade
```

## How to run

1. Install JDK 17 or higher, Maven (or use IntelliJ's built-in Maven), and MySQL Server 8.0. Make sure the MySQL service is running.
2. Clone the repository and open it in IntelliJ IDEA:
   ```
   git clone https://github.com/kal1kidan/StudentsJDBC.git
   ```
3. Copy `config.properties.example` to a new file named `config.properties` in the project root, and put in your own MySQL password:
   ```
   db.url=jdbc:mysql://localhost:3306/
   db.user=root
   db.password=YOUR_PASSWORD_HERE
   ```
4. Let Maven download the dependencies (IntelliJ does this automatically, or click the Maven reload button).
5. Run the classes one at a time, in order, from `Task1` to `Task6`, by clicking the green arrow next to `main`.

## Security note

The database password is **not** stored in the code or in this repository. It is read at run time from `config.properties`, which is listed in `.gitignore` and is never uploaded to GitHub. Only the template `config.properties.example` is included.
