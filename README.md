# StudentManager-Java

A simple desktop task manager I built to learn Java Swing and SQLite.
It lets you add tasks, view them in a table, save/load from a database, and export to CSV.

## Tech

- Java 21
- Swing (GUI)
- SQLite (persistence)
- Maven (build)

## Build and Run

Make sure you have Java 21 and Maven installed, then:

```
mvn clean package
java -jar target/StudentManager-1.0-SNAPSHOT.jar
```

Or just run it from your IDE if you prefer.

## Features

- Add tasks through a simple form
- View all tasks in a table
- Load tasks from SQLite database
- Save tasks to file
- Export tasks to CSV
