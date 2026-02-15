# Land Routing Service

A simple Spring Boot REST service that calculates a land route between two countries using border data.

The service loads country data from a public JSON source and computes the route using an efficient BFS (Breadth-First
Search) algorithm.

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
git clone https://github.com/RomeoStancu/land-routing-service.git
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

```bash
curl http://localhost:8080/routing/CZE/ITA
```

Response:

```json
{
  "route": [
    "CZE",
    "AUT",
    "ITA"
  ]
}
```

Invalid country code:

```bash
curl http://localhost:8080/routing/XCV/ITA
```

Response:

```json
{
  "timestamp": "2026-02-15T11:02:30Z",
  "error": "INVALID_COUNTRY",
  "message": "Country not found: XCV"
}
```

No land route found:

```bash
curl http://localhost:8080/routing/ISL/ITA
```

Response:

```json
{
  "timestamp": "2026-02-15T11:02:35Z",
  "error": "NO_ROUTE",
  "message": "No land route found from ISL to ITA"
}
```

### Data source

Country data is loaded from:
https://raw.githubusercontent.com/mledoze/countries/master/countries.json

### Implementation Notes

The route is computed using Breadth-First Search (BFS) to guarantee the shortest path.

Country data is loaded once at application startup and cached in memory.

Domain-specific exceptions (CountryNotFoundException, RouteNotFoundException) are mapped to HTTP responses using
@RestControllerAdvice.

API returns structured JSON errors for clarity and consistency.

The RoutingService checks if the origin and destination countries exist, then searches for a path. If no path is found,
it throws a NoRouteFoundException, converted into a 400 response. Invalid country codes throw CountryNotFoundException,
converted into a 400 response.

Author
Romeo Stancu