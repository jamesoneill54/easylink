# Neueda Home Assignment

This is a project developed as part of a home assignment for [Neueda Technologies](https://www.neueda.com/). This project aims to replicate the functionality of link shortening services like [bit.ly](https://bitly.com/) and [t.co](https://developer.twitter.com/en/docs/tco). The full project specification can be found [here](docs/assignment-spec.docx). 

## Project Implementation

I have implemented the project using [Spring Boot](https://spring.io/projects/spring-boot), and the following API endpoints are available upon running the application:

1. **Creating a link:** 
   
    `http://localhost:8081/create/<originalLink>`

    This will return a link that can be used in place of the other, longer link. 
   
2. **Using a link that has been created:**

    `http://localhost:8081/<providedLink>`
   
    This will lead the user to the page supplied when creating the link. 

3. **Metrics**
    
    `http://localhost:8081/metrics`
   
    This will lead the user to a metrics page, providing the following information. 
   
    1. How many overall visits the site has had.
    2. How many links have been created.
    3. How many times users have been forwarded to a destination site.
    
    Each metric can be gathered individually through the following API endpoints:
    
    1. `http://localhost:8081/metrics/visits`
    2. `http://localhost:8081/metrics/created`
    3. `http://localhost:8081/metrics/forwarded`
   
## Usage

## Design Decisions

**Links are verified before creating a shortened link**

This is to ensure dead or invalid links are not used by users. 