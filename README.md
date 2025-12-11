## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

# 📌 Prerequisites to Run This Project

This project uses Java Swing and MySQL Database.
Since the lib folder (containing external JARs) is not uploaded, you must set them up manually.


# ✅ 1. Install Required Software
✔ Java JDK (17 or above)

Download from Oracle or OpenJDK.

✔ MySQL Server & MySQL Workbench

You must have MySQL running.

# ✅ 2. Add Required JAR Files

This project uses the MySQL JDBC Driver, so download this:

➡ MySQL Connector/J

Download:
Search "MySQL Connector/J" and download the latest version (mysql-connector-j-x.x.xx.jar).

After downloading:

Create a folder named lib/ inside the project

Put the downloaded JAR file inside lib/

Add the JAR to your project classpath

Eclipse: Right-click project → Build Path → Add External Archives

IntelliJ: File → Project Structure → Libraries → Add JAR

# ✅ 3. MySQL Database Setup

Before running the project, create the database and table.

📌 Create Database

```
CREATE DATABASE todolist;
USE todolist;
```

📌 Create Table
```
CREATE TABLE todos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Pending'
);
```

# ✅ 4. Update Database Credentials

In your Java code (usually in DB connection class), update:

```
String url = "jdbc:mysql://localhost:3306/todolist";
String username = "root";
String password = "your_mysql_password";
```

# ✅ 5. Run the Application
Run the application by clicking on run button.