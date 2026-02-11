# Secure User Backend 🛡️

A Spring Boot application built to explore **Role-Based Authentication**, **Scheduled Tasks**, and **External API Integration**. 

This project was developed as a practical learning exercise to understand how backend systems secure user data and handle background automation in a real-world context.

## 🎓 Project Goal
To move beyond basic CRUD applications and implement industry-standard security practices (Spring Security) and architectural patterns (MVC, Service Layer) in a monolithic application.

## 🚀 Key Features implemented

* **🔐 Role-Based Access Control (RBAC):**
    * Implemented distinct authorities: `ROLE_ADMIN` (Full Access) vs `ROLE_USER` (Restricted).
    * Protected specific API endpoints using Spring Security's `SecurityFilterChain`.
* **🛡️ Secure Authentication:**
    * Integrated **BCrypt Hashing** to ensure passwords are never stored in plain text.
    * Built a custom `UserDetailsService` to load user data dynamically from the database.
* **⏱️ Automated Scheduling:**
    * Created background jobs using `@Scheduled` and **Cron expressions**.
    * Automates email notifications to users every 3 minutes.
* **☁️ External API Integration:**
    * Consumed the **OpenWeatherMap API** to fetch real-time data.
    * Used `RestTemplate` to parse JSON responses into Java POJOs.
* **⚠️ Global Exception Handling:**
    * Centralized error management using `@RestControllerAdvice` to handle runtime exceptions and SQL constraints cleanly.

## 🛠️ Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.x
* **Database:** MySQL (via `JdbcTemplate` for raw SQL understanding)
* **Security:** Spring Security, BCrypt
* **Tools:** Maven, Postman, IntelliJ IDEA

## ⚙️ Setup & Installation

1.  **Clone the Repository**
    ```bash
    git clone [https://github.com/rahulgheek/secure-user-backend.git](https://github.com/rahulgheek/secure-user-backend.git)
    cd secure-user-backend
    ```

2.  **Configure the Database**
    * Create a MySQL database named `demo_db` (or update the config).
    * *Note:* The SQL schema is included in `schema.sql` (if applicable).

3.  **Set Environment Variables**
    * This project uses `application.yml`. You must configure your own credentials in `src/main/resources/application.yml`:
    ```yaml
    spring:
      datasource:
        username: YOUR_DB_USERNAME
        password: YOUR_DB_PASSWORD
    weather:
      api:
        key: YOUR_OPENWEATHER_API_KEY
    ```

4.  **Run the Application**
    ```bash
    mvn spring-boot:run
    ```

## 🔌 API Endpoints

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/addUser` | Register a new user | Public |
| `POST` | `/addAdmin` | Register a new admin | **Admin Only** |
| `GET` | `/display` | View all users | **Admin Only** |
| `GET` | `/weather?city=Chandigarh` | Get current weather | Authenticated |
| `DELETE` | `/delete/{id}` | Delete a user | Authenticated |

## 🚧 Future Learning Path

* [ ] Refactor `JdbcTemplate` to **Spring Data JPA**.
* [ ] Implement **JWT (JSON Web Tokens)** for stateless session management.
* [ ] Add Unit Tests with **JUnit 5** and **Mockito**.
* [ ] Containerize the application using **Docker**.

## 👤 Author

**Rahul Gheek**
* *Computer Science Student & Backend Developer*
* Exploring: Java, Spring Boot, and Cloud Technologies.
