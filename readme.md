# 🍃 TestApi

A java Spring Boot API REST to manipulate Users

## Description: 
 
 - MVC disign.
 - Restfull.
 - Use Spring Boot and JPA.
 - Use a custom rate limiter.
 
## Database: 
 - Use a remote mysql database.
 - Include database [dump](./etc/database_dump/database.sql). 
 
## MainClass:
 - The main class is located on src/main/java/com/moimah/ecommerce/EcommerceApplication.java
 
## Requeriments:
- JDK 1.8.
- Maven 4.
- Spring boot.

## Architecture
- The API use MVC patron, the packages are organizated:
![image info](https://i.gyazo.com/8724dfc43a2af3e2dc0a95a46b29a2c8.png)
 * [entity](./src/main/java/com/moimah/ecommerce/entity): Contains the JPA/Hibernate entitie.
 * [dto](./src/main/java/com/moimah/ecommerce/dto): Contains data transfer objects to manipulate entities.
 * [repository](./src/main/java/com/moimah/ecommerce/repository): Contains JpaRepositories interfaces for each entity.
 * [controller](./src/main/java/com/moimah/ecommerce/controller): Contains the business logic and http actions to manipulate entity repositories.
 * [utils](./src/main/java/com/moimah/ecommerce/utils): Contains custom utils used in the application.
 * [application.properties](./.src/main/resources/application.properties): File that content spring properties, and others properties used in the app.

#### Rate limiter
[RateLimiter](./src/main/java/com/moimah/ecommerce/utils/ratelimiter/RateLimiter.java) is a custom util created to control the application request during a interval.
+ How to use: 
  -  add properties to [application.properties](./.src/main/resources/application.properties).
  ```
  ratelimiter.interval=1
  ratelimiter.unit=h
  ratelimiter.capacity=100
  ```
  This example allows 100 request in 1h. Allows (s, m, h, d).
  
  -  Inject rate limiter in controller:
  ```
  @Autowired
  private RateLimiter rateLimiter;     
  ```
  
  -  Add this code in the http functions in controller:
  ```
    if (!rateLimiter.getBucket().tryConsume(1)) {
        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
     }
   ```
  It should look like this:
  ![image info](https://i.gyazo.com/f7bb8c08522ba856da3c020f46a76da3.png)
  
### Predicates
+ createPredicate() The intention of this method is to create dynamic predicates using QueryDSL, to be used in the findAllByParams () and count () methods. This library allows java queries compatibles with all relational databases.
+ Native query, EntityManager has a function to create native queries. Example:
 ![image info](https://i.gyazo.com/08732c5e31aba35246ec3ae38e3604df.png)
  

### ModelMapper
+ Is an external library that allows convert Dto/Entity bidirectionally.
 ![image info](https://i.gyazo.com/5b719f95ec36948d99750ad17c28d626.png)
  
## How to use
### In IDE
1. Configure the server, datasource, jpa and ratelimiter properties.
2. Execute mvn clean install.
3. Run application.
## Executable files
- After execute mvn clean install the executables .jar, .war are located in [executables](./target).

