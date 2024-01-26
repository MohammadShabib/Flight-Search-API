# Flight Search API Application

# Table of Contents
1. [Summary](#summary)
2. [Key Features](#key-features)
3. [Technology Stack](#technology-stack)
4. [Getting Started](#getting-started)
5. [API Documentation](#api-documentation)
6. [To Do](#to-do)

## Summary

This application provides a comprehensive API for various flight-related operations. It is designed to cater to both one-way and round-trip flight searches, along with the capability to add multiple flights to the database in bulk. A notable feature of this application is its integration with the Amadeus Database, allowing it to fetch real-time information about flights.

### Key Features

1. **One-way Flight Search**: Enables users to search for single-leg flights based on specified criteria.
2. **Two-way Flight Search**: Facilitates the search for round-trip flights, enhancing the user's convenience.
3. **Bulk Flight Addition**: Offers the functionality to add a large number of flights to the database simultaneously, which is especially useful for administrative purposes.
4. **Real-time Flight Data**: Directly connects to the Amadeus Database for accessing real-time flight information, ensuring up-to-date and accurate flight data.

### Technology Stack

- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: Aerospike
- **Authentication**: JWT (JSON Web Tokens)


### Getting Started

To set up and run the application, follow these steps:

1. **Clone the Project**: Begin by cloning the repository to your local machine.

2. **Configure Application Properties**: In the project, find the `application.properties` file and update it with the following configuration settings:

   For Aerospike database configuration, use these values:
    ```
    aerospike.host.name=54f66803-a5ef-4328-a19d-af9caa028014.asdb.io
    aerospike.apiKeyId=6192fb7b629c4beab3305f3637dec96d
    aerospike.apiKeySecret=011c01efd55943e9a1893c4ffb79260c
    ```

   For Amadeus security settings, use these values:
    ```
    security.amadeus.clientId=ozH5U6xhACfGKJkF4n31xM2nKpATnrHt
    security.amadeus.clientSecret=sbzRL3i6A60why05
    ```

3. **Build and Run the Application**: Proceed with building and running the application

### Important Note:

- The configuration values provided above are for demonstration purposes and can be invalid after sometime and btw they are not linked to personal accounts :D.

### API Documentation

The APIs of this application are fully documented using Swagger, providing a clear and interactive interface for understanding and testing the available endpoints.

To access the API documentation, follow these steps:

1. **Run the Application**: Ensure that the application is running on your local machine or deployment server.

2. **Access Swagger UI**: Open a web browser and navigate to the Swagger UI using the following URL pattern:
   http://localhost:8080/swagger-ui/index.html

![Amadeus Database Search OneWay](https://github.com/MohammadShabib/Flight-Search-API-/assets/72793336/481f0174-e7fc-48e0-a693-570c8bbdccc1)
![JWT Authentication png](https://github.com/MohammadShabib/Flight-Search-API-/assets/72793336/86e5ce96-6631-4d1d-93e9-bdfb25e5043d)
![Local Database Search OneWay](https://github.com/MohammadShabib/Flight-Search-API-/assets/72793336/f321eb8c-9e67-45f3-80d3-b4ac2f8835a7)
![Add Flights](https://github.com/MohammadShabib/Flight-Search-API-/assets/72793336/22a3ae56-5678-45e7-8185-b3be254114b2)
![Local Database Search TwoWay](https://github.com/MohammadShabib/Flight-Search-API-/assets/72793336/e0ef3375-5fba-4a04-9140-f74a3a275164)
### To Do


1. **Implement CRUD APIs for Flight and Airport Objects**:
    - Develop and integrate APIs to perform Create, Read, Update, and Delete (CRUD) operations for both Flight and Airport objects.
    - Ensure these APIs are connected with the corresponding CRUD operations on the database, allowing for seamless data management.

2. **Enable Logging**:
    - Implement logging throughout the application to capture critical information, errors, and operational data.

3. **Exception Handling and Failure Management**   
4. **Add JUnit Tests**
   