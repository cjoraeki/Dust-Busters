# Dust-Busters

This Spring Boot application provides a complete admin dashboard for managing cleaning tasks. Here's what I've built:

### Key Features:

**Task Management**
1. Create, edit, and delete cleaning tasks
2. Assign tasks to cleaners
3. Track task status (pending, in-progress, completed, cancelled)

**Cleaner Management**
1. Add, edit, and delete cleaner profiles
2. Track cleaner availability
3. View tasks assigned to each cleaner

**Dashboard Overview**
1. Summary cards showing task counts by status
2. Today's scheduled tasks
3. Available cleaners list

**Security**
1. Login authentication
2. Protected admin routes

### Technical Details:
- Built with Java 17 and Spring Boot 3.3.11
- Uses Spring MVC for web layer
- Spring Data JPA for database operations
- Thymeleaf for server-side templating
- Bootstrap 5 for responsive UI
- H2 in-memory database for data storage