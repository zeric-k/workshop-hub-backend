# 🩰 Workshop App - Backend Service

This is the **backend service** for the Workshop App — a platform to discover, book, and manage dance workshops and spaces.  
It powers the REST APIs for users, spaces, workshops, bookings, and payments, and integrates seamlessly with the frontend (React) client.

---

## ✨ Features

- 🔐 **User Authentication & Authorization** (JWT based)
- 🏢 **Spaces Management** – create and manage workshop spaces
- 📅 **Workshops API** – filter by category, level, and space
- 💳 **Payments API** – manage bookings & track payments
- 📊 **Booking Workflow** – users can book/cancel workshops as well as spaces
- 🌐 **CORS enabled** for frontend integration
- 🗄️ **Azure SQL Database** with JPA/Hibernate

---

## 🏗️ Tech Stack

- **Java 11**  
- **Spring Boot 2** (Spring Web, Spring Security, Spring Data JPA)  
- **Azure SQL Database** (production)  
- **H2 Database** (local demo/testing)  
- **Maven** for build & dependency management  
- **JWT** for authentication & security  
- **Deployed on:** Azure App Service  

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/workshop-backend.git
cd workshop-backend

2. Configure Database

This project supports two database setups:

🔹 Local (default – H2 in-memory DB)

Runs instantly with no setup required.
Configured in application.properties:

# H2 database (default)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true


Access H2 console:
👉 http://localhost:8080/h2-console
(JDBC URL: jdbc:h2:mem:testdb)

🔹 Production (Azure SQL)

Uncomment & configure these in application-prod.properties:

spring.datasource.url=jdbc:sqlserver://<your-server-name>.database.windows.net:1433;database=<your-db-name>
spring.datasource.username=<your-db-username>
spring.datasource.password=<your-db-password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect


Run with:

mvn spring-boot:run -Dspring-boot.run.profiles=prod

3. Run the service
mvn spring-boot:run


Backend will start at:
👉 http://localhost:8080

📡 API Endpoints
🔑 Authentication (/auth)
Method	Endpoint	Description	Request Body
POST	/auth/login	Authenticate user & return JWT	{ "username": "...", "password": "..." }
POST	/auth/register	Register a new user	{ "username": "...", "password": "...", "email": "..." }
🎭 Workshops & Spaces (/api/v1)
Method	Endpoint	Description
GET	/api/v1/workshops	Get list of workshops (filters: category, level, spaceId, pageNo, pageSize, userId)
POST	/api/v1/workshops	Create a new workshop
GET	/api/v1/spaces	Get list of spaces
POST	/api/v1/spaces	Create a new space

Example Workshop Request:

{
  "title": "Hip Hop Beginner Session",
  "date": "2025-08-30",
  "level": "Beginner",
  "category": "Hip Hop",
  "instructor": "John Doe",
  "link": "https://zoom.us/...",
  "price": 499,
  "spaceId": 1
}

💳 Payments (/api/payments)
Method	Endpoint	Description
POST	/api/payments/create	Create a payment (status = PENDING)
POST	/api/payments/confirm/{paymentId}	Confirm payment (mock success)
GET	/api/payments/user/{userId}	Get all payments for a user

Example Payment Request:

{
  "userId": 1,
  "workshopId": 101,
  "amount": 499.0
}

📊 Booking Workflow

User registers & logs in → receives JWT token

User browses workshops & spaces (via /api/v1/workshops and /api/v1/spaces)

User books workshop → creates payment (/api/payments/create)

System confirms payment (/api/payments/confirm/{id})

User can later fetch their booking/payment history.

🧪 Testing

Run tests with:

mvn test

📂 Project Structure
src/main/java/com/workshop
 ├── controller     # REST controllers
 ├── dto            # Data Transfer Objects
 ├── entity         # JPA entities
 ├── repository     # Spring Data JPA repositories
 ├── service        # Business logic
 └── util           # Utilities (JWT, filters, etc.)

📊 Architecture
flowchart TD
    User -->|HTTP/HTTPS| Frontend[React Frontend]
    Frontend -->|REST API| Backend[Spring Boot Backend]
    Backend -->|JPA/Hibernate| AzureSQL[(Azure SQL Database)]
    Backend -->|JWT| Auth[Authentication Service]

🔗 Related Repositories

Workshop App Frontend

📄 License

This project is licensed under the MIT License.
Feel free to use and modify for your own projects.

👨‍💻 Author

Your Name
Portfolio
 | LinkedIn
 | GitHub: https://github.com/zeric-k



---
