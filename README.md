## Tech stack used in this project
* Spring Boot
* Jersey Framework
* JPA
* Java 8
* Docker Compose

## Project Introduction
This project demonstrates backend RESTful API under Jersey framework. User can call API to get single book, list of books, 
author information, and publisher information at this moment. Further functionality is under development...  

####Jersey resource configuration  
> Each controller must be included in jersey resource config under *src/main/java/JerseyConfig* 
```java
@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig{
    public JerseyConfig() {
        register(BookController.class);
        // add more controller here...
    }
}
```







