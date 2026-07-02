<!--- # "Can be a image or a gift from the project pages" -->

<p align="center">
  <img src="https://via.placeholder.com/800x400?text=Spring+Boot+Product+API" alt="Product API">
</p>

# Product Management API (CRUD Spring Boot)

A RESTful API built with Spring Boot for managing products. This system provides comprehensive CRUD operations through the `ProductController`, handles domain rules using a `GlobalExceptionHandler`, and exposes API documentation via Swagger/OpenAPI (`OpenApiConfig`). Data transfer is optimized using Java Records (`ProductRecordDto` and `ProductResponseDto`).

## Tech Stack

<!--- # "Verify icons availability here https://github.com/tandpfun/skill-icons" -->

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven,postgres,postman)](https://skillicons.dev)

## Getting Started

1. **Clone project**: `git clone https://github.com/JVGirardi/crud-springboot.git`
2. **Install Dependencies**: Run `./mvnw clean install` or use your local Maven installation.
3. **Database Setup**: Follow the instructions in the `subindo bd.txt` file and configure your database credentials in `src/main/resources/application.properties`.
4. **Start Developing**: Execute the `SpringbootApplication.java` main class to start the application server.
5. **Access Documentation**: Navigate to the Swagger UI endpoint (usually `http://localhost:8080/swagger-ui.html`) to test the API endpoints.

## Contribute

1. **Clone project**: `git clone https://github.com/JVGirardi/crud-springboot.git`
2. **Create feature/branch**: `git checkout -b feature/NAME`
3. **Commit changes**: `git commit -m 'Add some feature'`
4. **Push to branch**: `git push origin feature/NAME`
5. **Open a Pull Request**

## License

This software is available under the following licenses:

- [MIT](https://rem.mit-license.org)
