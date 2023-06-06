# The Airport API

The Airport API is a REST API that allows users to query various information about airports and provides several reports.

## Functional Requirements

The Airport API has the following functional requirements:

### Query

- Query airport information (airport, navaid, runways, frequencies) based on the airport ident.
- Query all airports within a country using either the country code (e.g., NL) or the full name (e.g., Netherlands).

### Reports

- Generate a report showing the 10 countries with the highest number of active/open airports, including the count.
- Generate a report showing the 10 countries with the lowest number of active/open airports.
- Generate a report showing the number of airports grouped by type and country.
- Generate a report showing the average length of runways per country.
- Generate a report showing the average length of runways with a runway heading between 175 and 185 per country.

## Microservice structure:

- application layer for implement services and logic.
- config layer for configuration files
- domain layer for database repositories and entities.
- exception layer for implement exceptions and exception handler.
- mapper layer for implement mapping class between layers.
- presentation layer for APIs

## How to Use My Project:

1. Clone the repository to your local machine.
2. Set up your development environment, ensuring you have Java, Spring Boot, and PostgreSQL installed.
3. Import the project into your preferred IDE.
4. Configure the database connection in the application properties file.
5. Run the AirportApplication and verify that it starts successfully.
6. Access the SwaggerUI documentation to explore the available endpoints and their documentation
   (http://localhost:8080/swagger-ui/index.html#).
7. Use the provided Postman collections to test and interact with the API(airportAPIs.postman_collection).

## Resources

The resource files for this assessment have been extracted from
  [https://ourairports.com/data/](https://ourairports.com/data/).

