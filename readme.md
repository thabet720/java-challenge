### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)
- Use Docker: Build the image and run the container. Run these commands in the project root(build command: `docker build -t api-demo .`  , run container command:`docker run -p 8080:8080 api-demo`).

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.

#### Improvements
- Added tests for the EmployeeController and EmployeeServiceImpl Classes
- Added Validation for requests
- Extended the project structure By adding DTO and converter layers
- Added Exception Handling
- Added endpoints responses
- Secured endpoints with basic auth (username and password are admin)
- Dockerized the API app
- Added Caching using EhCache
- Added documentation and comments
- Fixed a bug when creating a new employee

#### Technical Dept And Future Improvements
- Increase the test coverage by adding more tests
- Unify the endpoints responses more and cover more exception
- Use a different auth mechanism (e.g JWT) instead Basic auth
- Add a user service and user table implementation to avoid using the In-Memory user for the Auth
- Improve cache configuration for better performance

#### Experience in Java
- Been a software developer for 5 years and I used mostly Java 
- For Spring/ Spring boot, if I can sum up my experience with it to around 3 years in total. I'm familiar with most of its features.

