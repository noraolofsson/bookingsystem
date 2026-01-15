# Meeting Room Booking System

A RESTful API for managing meeting room reservations, built with **Java**, **Spring Boot**, and **PostgreSQL**.

This project demonstrates a production-ready backend architecture focusing on data integrity, separation of concerns, and scalable system design.

---

## Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 4.0.0 (Web, Data JPA, Validation)
* **Database:** PostgreSQL
* **Containerization:** Docker & Docker Compose
* **Testing:** JUnit 5, Mockito
* **Tools:** Maven, Lombok

---

## Key Features

* **Concurrency Handling:** Prevents double-bookings by checking overlapping time slots in the database layer before confirming a reservation.
* **Clean Architecture:** Strict separation between Controller, Service, and Repository layers.
* **DTO Pattern:** Uses Java `records` to separate internal database entities from the public API, ensuring security and flexibility.
* **Dockerized Environment:** The database is fully containerized for easy setup.

---

## Architecture & Design Decisions

The application follows a layered architecture to ensure maintainability and testability.

Service Layer handles Business Logic: The controller is "dumb" and only handles HTTP requests. All validation (e.g., Is the room free?) happens in the Service.

DTOs (Data Transfer Objects): I chose to use Records for DTOs to ensure immutability. This prevents exposing the raw Database Entities to the client, solving issues like infinite recursion and over-fetching data.

Transactional Integrity: Critical operations use @Transactional to ensure that booking data remains consistent even if a server error occurs mid-process.

---

## Getting started
1. Clone the repository:  
git clone [https://github.com/DIN-ANVÄNDARE/bookingsystem.git](https://github.com/DIN-ANVÄNDARE/bookingsystem.git)  
cd bookingsystem  

2. start the database:  
docker-compose up -d

3. Run the Application:  
mvn spring-boot:run

The server will start on http://localhost:8080.

---
## API Reference

