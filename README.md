# Movie List App

Movie List App is a simple Android application that displays a list of movies along with their details using a `RecyclerView`.

## Features

- Displays a list of movies
- Each movie item shows the movie title, release date, and description
- Uses `RecyclerView` for efficient display of movie items
- Follows MVVM (Model-View-ViewModel) architecture
- Uses ViewBinding for easy UI reference

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/movielistapp.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an Android device or emulator.

## Usage

1. Launch the app.
2. Scroll through the list of movies.
3. Tap on a movie item to see more details.

## Code Overview

### Main Components

- `Movie`: Data class representing a movie.
- `AddEditMovie: Class that applies the CRUD operations on the movies .
- `MovieAdapter`: RecyclerView Adapter to bind movie data to the views.
- `MainActivity`: The main activity that initializes the RecyclerView and sets up the adapter.

### Layout Files

- `activity_main.xml`: Contains the `RecyclerView` to display the list of movies.
- `movie_item.xml`: Layout for each movie item in the list.

### Dependencies

- RecyclerView: For displaying the list of movies.
- ViewModel: For managing UI-related data in a lifecycle-conscious way.
- ViewBinding: For binding UI components in a safe and efficient way.

### Example Code

#### Movie.java
```java
public class Movie {
    String title;
    String Author;
    String Genre ;
    String duration ;
    // Constructor, getters and setters
}
