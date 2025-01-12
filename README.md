# Library Management System

## Project Overview
A JavaFX-based Library Management System to efficiently manage books, users, borrowing, and returning of books, as well as overdue fines. The system uses a layered architecture to ensure modularity and maintainability.

---

## Layered Architecture
The project follows a three-layered architecture:

1. **Presentation Layer (UI Layer):**
   - Handles user interactions and displays the application interface.
   - Built using JavaFX with JFoenix components for a modern look and feel.
   - Uses controllers to communicate with the service layer.

2. **Service Layer (Business Logic Layer):**
   - Contains the core business logic of the application.
   - Acts as an intermediary between the Presentation Layer and the Data Access Layer.
   - Ensures proper validation and exception handling.

3. **Data Access Layer (Persistence Layer):**
   - Handles all database operations.
   - Uses Hibernate for ORM to interact with the MySQL/SQLite database.
   - Includes entity classes and repositories to manage database tables.

---

## Project Structure
```
LibraryManagementSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.example.library/
│   │   │   │   ├── controller/      # Presentation Layer (JavaFX Controllers)
│   │   │   │   ├── service/         # Service Layer (Business Logic)
│   │   │   │   ├── model/           # Entity Classes (Database Tables)
│   │   │   │   ├── repository/      # Data Access Layer (Hibernate Repositories)
│   │   │   │   ├── util/            # Utility Classes (e.g., Database Connection)
│   │   ├── resources/
│   │   │   ├── fxml/                # FXML Files for UI Layouts
│   │   │   ├── css/                 # Stylesheets for UI
│   │   │   ├── images/              # Images and Icons
│   ├── test/                        # Unit Tests
├── pom.xml                          # Maven Configuration File
├── README.md                        # Project Documentation
```

---

## Features

### 1. Book Management
- Add, update, delete, and search books.
- Fields include ISBN, Title, Author, Genre, and Availability.

### 2. User Management
- Add, update, delete, and search users.
- Fields include Name, ID, Contact Information, and Membership Date.

### 3. Borrow and Return Books
- Borrow and return functionality with borrowing history.
- Fine calculation for overdue returns.

### 4. Fine Management
- Automated fine calculation.
- Fine payment tracking.

### 5. Reporting
- Generate reports for available, borrowed, and overdue books.

---

## Technologies Used
- **Frontend:** JavaFX with JFoenix
- **Backend:** Java (Layered Architecture)
- **Database:** MySQL/SQLite with Hibernate ORM
- **Build Tool:** Maven
