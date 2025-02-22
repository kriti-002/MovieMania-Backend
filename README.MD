# **MovieMania - Spring Boot Application**

## **Overview**

MovieMania is a Spring Boot-based backend application that provides a RESTful API for managing movies, reviews, theaters, shows, and user accounts. Additionally, it includes a sentiment analysis feature for user reviews, implemented in a Jupyter Notebook and deployed via a separate Flask backend API that runs in parallel.

## **Technologies Used**

- **Backend Framework**: Spring Boot
- **Security**: JWT (JSON Web Tokens) for Authentication
- **Database**: MySQL (or JPA compatible databases)
- **Build Tool**: Maven
- **API Testing**: Swagger UI, Postman
- **Machine Learning**: Sentiment Analysis using Natural Language Processing (NLP) with Flask API
- **Notebook**: Jupyter Notebook for implementing sentiment analysis logic

## **Project Structure**

The project follows a layered architecture to separate concerns and promote clean code practices. Below is a breakdown of the main packages:

```plaintext
com.demo.MovieMania
├── Config
│   ├── ApplicationConfig          // Main application configurations
│   ├── JwtAuthenticationFilter    // JWT filter for securing endpoints
│   ├── RestConfig                 // REST configuration for the application
│   └── SecurityConfig             // Security settings and JWT configurations
│
├── Controller
│   ├── MovieController            // API endpoints for movie operations
│   ├── ReviewController           // API endpoints for review operations
│   ├── ShowController             // API endpoints for show operations
│   ├── TheatreController          // API endpoints for theatre operations
│   └── UserController             // API endpoints for user operations
│
├── Model
│   ├── Domain
│   │   ├── Enums                  // Enumerations for different constants
│   │   │   ├── Genre              // Enum for movie genres
│   │   │   ├── Role               // Enum for user roles (Admin, User, etc.)
│   │   │   └── Seat               // Enum for seat types
│   │   ├── Movie                  // Entity for movies
│   │   ├── Review                 // Entity for movie reviews
│   │   ├── Shows                  // Entity for show details
│   │   ├── ShowSeat               // Entity for seats in a show
│   │   ├── Theatre                // Entity for theatre details
│   │   ├── TheatreSeat            // Entity for theatre seats
│   │   └── User                   // Entity for user details
│   ├── Request                    // Classes for handling API request bodies
│   └── Response                   // Classes for sending API responses
│
├── Repository                     // Interfaces for data access to the database
│
├── Service
│   ├── ApiService                 // Generic service layer for common operations
│   ├── AuthenticationService      // Service for user authentication and JWT handling
│   ├── JwtService                 // Service to manage JWT generation and validation
│   ├── MovieService               // Service for business logic related to movies
│   ├── ReviewService              // Service for handling reviews
│   ├── ShowService                // Service for show management
│   └── TheatreService             // Service for theatre management
│
└── resources
    └── application.properties     // Configuration properties for the Spring Boot application
```

## **Setup Instructions**

1. **Clone the repository**:

   ```bash
   git clone <repository_url>
   cd MovieMania
   ```

2. **Database Configuration**:
   
   - Create a database (e.g., `movie_mania_db`) in MySQL or any JPA-compatible database.
   - Update the database configuration in `src/main/resources/application.properties`:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/movie_mania_db
     spring.datasource.username=<your_db_username>
     spring.datasource.password=<your_db_password>
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build the Project**:

   ```bash
   mvn clean install
   ```

4. **Run the Application**:

   ```bash
   mvn spring-boot:run
   ```

5. **Sentiment Analysis Flask API**:
   
   - Navigate to the Jupyter Notebook folder that contains the sentiment analysis logic.
   - Run the notebook using a Jupyter environment.
   - Deploy the Flask API using the notebook:

     ```bash
     jupyter notebook
     # Open the notebook and follow the instructions to deploy the Flask API.
     ```

6. **API Documentation**:
   - Access Swagger UI at `http://localhost:8080/swagger-ui.html` if Swagger is enabled.

## **API Endpoints**

### **Auth Endpoints - Accessible to all**
- **Sign Up**: `POST /auth/add`
- **Sign In**: `POST /auth/login`

### **Admin Endpoints**

#### **User Management**
- **Delete User**: `DELETE /admin/delete?id={id}`
- **Update User**: `PUT /admin/update?id={id}`
- **Get User**: `GET /admin/get?id={id}`
- **Get My Details**: `GET /admin/me`

#### **Movie Management**
- **Create a Movie**: `POST /admin/movie/add`
- **Update a Movie**: `PUT /admin/movie/update?id={movieId}`
- **Delete a Movie**: `DELETE /admin/movie/delete?id={movieId}`
- **Get All Movies**: `GET /admin/movie/getAll`
- **Get All Reviews of a Movie**: `GET /admin/movie/getReviews?id={movieId}`

#### **Review Management**
- **Delete a Review**: `DELETE /admin/review/delete?id={reviewId}`

#### **Theatre Management**
- **Add a Theatre**: `POST /admin/theatre/add`
- **Update a Theatre**: `PUT /admin/theatre/update?id={theatreId}`
- **Delete a Theatre**: `DELETE /admin/theatre/delete?id={theatreId}`

#### **Show Management**
- **Add a Show**: `POST /admin/shows/add`
- **Delete a Show**: `DELETE /admin/shows/delete?id={showId}`

### **User Endpoints**

#### **User Favourites Management**
- **Get Favourites**: `/user/getFavourite?userId={id}`
- **Post Favourites**: `POST /user/addFavourite?userId={id1}&movieId={id2}`

### **Movie Requests**
- **Find movie by genre**: `GET /user/movie/getMovieByGenre?genre={genre`
- **Find Movie by Title**: `GET /user/movie/getMovieByTitle?title={title}`
- **Get all movies**: `GET /user/movie/getAll`
- **Find movies by Rating**: `GET /user/movie/getMovieByRating?ratings={rating}`
- **Find movie by Length**: `GET /user/movie/getMovieByLength?time={length}`

### **Review Requests**
- **Add Reviews**: `POST /user/review/add`
- **Find Review by Id**: `GET /user/review/getById?id={reviewId}`
- **Delete Review**: `DELETE /user/review/delete?id={reviewId}`
- **Update Review**: `PUT /user/review/update?id={reviewId}`
- **Get all Reviews of a movie**: `GET /user/review/getAll?id={movieId}`

### **Search By**
- **Search by Theatre Name, City, or Movie Title**: `GET /user/show/search?theatreName={theatreName}&city={city}&movieTitle={movieTitle}`

### **Show Requests**
- **Get Available Seats of a Show**: `GET /user/show/getAvailableSeats?id={id}`

### **Sentiment Analysis Endpoint**

- **Analyze Sentiment of a Review**: `POST http://127.0.0.1:5000/analyze`
  - **Description**: This endpoint communicates with the Flask API (implemented in a Jupyter Notebook) to evaluate the sentiment (Positive/Negative/Neutral) of a movie review.

## **Model Structure**

### **Enums**
- **Genre**: Enum representing different movie genres (e.g., Action, Comedy, Drama, Thriller, Sci-fi, etc.).
- **Role**: Enum for user roles, such as Admin, User, etc.
- **Seat**: Enum for seat types in a theatre (e.g., Regular and Recliner).

### **Domain Entities**
- **Movie**: Represents a movie with attributes like title, genre, casts, release date, ratings, etc.
- **Review**: Represents a user review for a movie, including text and rating.
- **Theatre**: Represents a theatre with attributes like name, location, available seats, etc.
- **Show**: Represents a movie show, including time, movie details, and seating.

## **Sentiment Analysis**

Sentiment Analysis is performed using a Flask API that analyzes user reviews' sentiment to determine if they are Positive, Negative, or Neutral. The analysis is powered by NLP algorithms implemented in a Jupyter Notebook.

