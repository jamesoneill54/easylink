# Neueda Home Assignment

This is a project developed as part of a home assignment for [Neueda Technologies](https://www.neueda.com/). This project aims to replicate the functionality of link shortening services like [bit.ly](https://bitly.com/) and [t.co](https://developer.twitter.com/en/docs/tco). The full project specification can be found [here](docs/assignment-spec.docx). 

## Project Implementation

I have implemented the project using [Spring Boot](https://spring.io/projects/spring-boot), and the following API endpoints are available upon running the application:

1. **Creating a link:** 
   
    Post request to this address: `http://localhost:8081/create/`

    Request body should be: 

    ```
    {"link": "http://www.google.com"}
    ```

    This will return a link that can be used in place of the other, longer link. 
   
2. **Using a link that has been created:**

    `http://localhost:8081/<providedLink>`
   
    This will lead the user to the page supplied when creating the link. 

### Proposed Features

**Metrics**
    
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

### Packaging and running the application

1. Package the application:
   
   From the root directory of the project, run the following command:
   ```
   mvn package
   ```

2. Build the docker image:

   ```
   docker build --file=Dockerfile --tag=easylink-server:latest --rm=true .
   ```

3. Run the docker image:

   ```
   docker run --name=easylink-server --publish=8081:8081 easylink-server:latest
   ```
   
### Stopping the application

The application can be stopped by stopping the docker container. 

1. Find the docker container ID using `docker ps`.
2. Stop the container using `docker stop <container-id>`.