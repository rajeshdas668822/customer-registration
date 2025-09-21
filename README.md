# Customer Registration System

## What is this project?

This is a **Customer Registration System** - a web application that helps businesses store and manage customer information. Think of it like a digital address book for companies, but much more powerful and organized.

## What does it do?

The system allows you to:
- **Store customer details** like names, email addresses, phone numbers
- **Retrieve customer information** quickly through a web interface
- **Manage customer data** in a secure and organized way

## How does it work?

Imagine you run a business and need to keep track of your customers. Instead of using paper files or spreadsheets, this system:

1. **Stores customer information** in a database (like a digital filing cabinet)
2. **Provides a web interface** where you can view customer details
3. **Uses modern technology** to handle many customers at once without slowing down

## Technical Overview

This is a **Java-based web application** built using modern technologies:

### Architecture
The project is organized into 5 main parts (modules):

1. **customer-registration-boot** - The main application that starts everything
2. **customer-registration-web** - Handles web requests (like when someone visits the website)
3. **customer-registration-data** - Manages database operations (storing and retrieving customer info)
4. **customer-registration-config** - Contains configuration settings
5. **customer-registration-app** - Application-specific logic

### Key Technologies Used

- **Spring Boot** - A framework that makes building web applications easier
- **R2DBC with PostgreSQL** - Modern database technology that can handle many requests simultaneously
- **Reactive Programming** - Allows the system to handle multiple users efficiently
- **Maven** - Tool for managing project dependencies and building the application

### What information does it store?

For each customer, the system stores:
- Customer ID (unique identifier)
- First Name
- Last Name
- Email Address
- Mobile Phone Number
- Home Phone Number

## How to run this project?

### Prerequisites (What you need first)
1. **Java 8 or higher** installed on your computer
2. **PostgreSQL database** running on your machine
3. **Maven** build tool installed

### Setup Steps

1. **Database Setup**
   - Install PostgreSQL
   - Create a database named `customer`
   - Make sure it's running on `localhost:5432`
   - Set username as `postgres` and password as `Password1`

2. **Run the Application**
   ```bash
   # Navigate to the project folder
   cd customer-registration
   
   # Build the project
   mvn clean install
   
   # Run the application
   cd customer-registration-boot
   mvn spring-boot:run
   ```

3. **Access the Application**
   - Open your web browser
   - Go to: `http://localhost:8090`
   - You should see customer data (if any exists in the database)

## Project Structure Explained

```
customer-registration/
├── customer-registration-boot/     # Main application starter
├── customer-registration-web/      # Web controllers (handles web requests)
├── customer-registration-data/     # Database models and repositories
├── customer-registration-config/   # Configuration files
├── customer-registration-app/      # Application logic
└── pom.xml                        # Main project configuration
```

## API Endpoints

Currently, the system provides:
- **GET /** - Retrieves all customers in the system

## Database Schema

The system uses a simple `customer` table with these fields:
- `customer_id` (Primary Key)
- `first_name`
- `last_name` 
- `email`
- `mobile`
- `home_phone`

## Future Enhancements

This system could be extended to include:
- Adding new customers through web forms
- Updating existing customer information
- Deleting customers
- Search functionality
- Customer address management
- Authentication and security features

## For Developers

### Code Quality Notes
The code review identified several areas for improvement:
- Field injection should be replaced with constructor injection
- Some performance optimizations needed
- Better error handling required
- Naming consistency improvements needed

### Technology Stack
- **Framework**: Spring Boot 2.1.9
- **Database**: PostgreSQL with R2DBC (Reactive Database Connectivity)
- **Build Tool**: Maven
- **Java Version**: 8+
- **Architecture**: Reactive/Non-blocking

## Support

If you encounter any issues:
1. Make sure PostgreSQL is running
2. Check that the database `customer` exists
3. Verify the database credentials in `application.properties`
4. Ensure Java and Maven are properly installed

---

*This project demonstrates modern Java web development practices using reactive programming and microservices architecture.*