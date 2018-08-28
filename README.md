# Game Love

Game love service that keeps track of the games that the player loves.

## Explanation

I used [h2](http://www.h2database.com/html/main.html) for database and [swagger](https://swagger.io/) for documentation. 
You can use swagger for testing.

* h2 console : [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
* Swagger url : [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/)

### [Tree](https://www.petrikainulainen.net/software-development/design/understanding-spring-web-application-architecture-the-classic-way/)

* main
    * java
        * com.comeon.gamelove
            * bootstrap     -> insert dummy data to database
            * configuration -> configurations
            * controller    -> api process for client
            * converter     -> converter models to entities or entities to models
            * domain        -> entities for db
            * error         -> error handler for services
            * model         -> entities for api
            * repository    -> db process
            * service       -> repository process for controller 
            * GameLoveApplication.java -> You can start project with this class.
    * resources
        * application.yaml -> project configuration
        * static
        * templates


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.comeon.gamelove.GameLoveApplication` class from your IDE.
- You should install to [Lombok](https://projectlombok.org/)into ide.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Requirements

For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Spring Boot](https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/htmlsingle/)