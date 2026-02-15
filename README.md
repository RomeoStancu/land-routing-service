# Land Routing Service

A simple Spring Boot REST service that calculates a land route between two countries using border data.

The service loads country data from a public JSON source and computes the route using an efficient BFS (Breadth-First Search) algorithm.

---

## Features

- Computes land route between two countries using border information
- REST endpoint: `GET /routing/{origin}/{destination}`
- Returns single shortest route if exists
- Returns proper HTTP errors for:
    - Invalid country codes
    - No land route found
- Uses Spring Boot + Maven
- Includes unit tests

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Maven
- Jackson
- JUnit 5

---

## Build & Run Instructions

### Clone the repository

```bash
git clone https://github.com/<your-username>/land-routing-service.git
cd land-routing-service
```

### Build the application
```bash
mvn clean package
```
This will:
- Download dependencies
- Compile the project
- Run tests
- Build the JAR file
- 
### Run the application
```bash
mvn spring-boot:run
```
or
```bash
java -jar target/land-routing-service-0.0.1-SNAPSHOT.jar
```
### Running Tests
```bash
mvn test
```
### API Usage
Successful route:
curl http://localhost:8080/routing/CZE/ITA
Response:
{
"route": ["CZE", "AUT", "ITA"]
}
Invalid country code:
curl http://localhost:8080/routing/XXX/ITA
Response:
{
"timestamp": "2026-02-15T11:02:30Z",
"error": "INVALID_COUNTRY",
"message": "Country not found: XXX"
}
### DATA SOURCE
Country data is loaded from:
https://raw.githubusercontent.com/mledoze/countries/master/countries.json