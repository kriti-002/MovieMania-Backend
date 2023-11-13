### MovieMania Application
The Movie Backend Application is a Spring Boot-based backend service for managing movie-related data. It provides RESTful APIs to perform operations on movies, reviews, shows, and theaters, with additional administrative capabilities for creating, updating, and deleting records.

# Admin Requests
This collection contains all the Admin related requests.

- Movie: Create, Update, Delete, Getting All movies, Get All Reviews of a movie.
  - Adding a Movie: localhost:8080/admin/movie/add
  - Updating a Movie: localhost:8080/admin/movie/update?id=someNumber
  - Deleting a Movie: localhost:8080/admin/movie/delete?id=someNumber
  - Get all Movies: localhost:8080/admin/movie/getAll
  - Get all Reviews of a Movie: localhost:8080/admin/movie/getReviews?id=someNumber
  
- Review: Deleting a Review(By Id).
  - Deleting a Review: localhost:8080/admin/movie/review?id=someNumber

- Theatre: Adding, Updating and Deleting a Theatre.
  - Add Theatre: localhost:8080/admin/theatre/add
  - Delete Theatre: localhost:8080/admin/theatre/delete?id=someNumber
  - Update Theatre: localhost:8080/admin/theatre/update?id=someNumber

- Show: Add and Delete Show.
  - Add Show: localhost:8080/admin/shows/add
  - Delete Show: localhost:8080/admin/shows/delete?id=someNumber


# Movie Requests
This collection contains the endpoints which helps to retrieve the movie by its attributes namely Genre, Title, Rating, Length and all the movies.
- Get Movies by Genre: localhost:8080/movie/getMovieByGenre?genre=yourGenre
- Get Movie by Title: localhost:8080/movie/getMovieByTitle?title=movieTitle
- Get All Movies: localhost:8080/movie/getAll
- Get Movies Greater than or equal to some Ratings: localhost:8080/movie/getMovieByRating?ratings=someRating
- Get Movies by Length: localhost:8080/movie/getMovieByLength?time=someTime

# Review Requests
This collection contains the endpoints which helps to create, delete, update and retrieve the reviews by its attributes namely id and all the reviews.
- Create Review: localhost:8080/review/add
- Delete Review: localhost:8080/review/delete?id=someID
- Update Review: localhost:8080/review/update?id=someID
- Find Review by ID: localhost:8080/review/getById?id=someID
- Get all Reviews of a Movie: localhost:8080/review/getAll?movieId=someID

# Show Requests
This allows us to check all the available seats of a show.
- Get Available Seats: localhost:8080/show/getAvailableSeats?id=someID
