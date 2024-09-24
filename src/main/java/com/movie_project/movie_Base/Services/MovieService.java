package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.Entity.Director;
import com.movie_project.movie_Base.Entity.Movie;
import com.movie_project.movie_Base.Enum.GENRE;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie addMovie(Movie movie);
    Movie saveMovie(Movie movie);
    Movie updateMovie(Movie movie);
    void deleteMovie(Long id);
    Movie addDirector(Movie movie,Director director);
    List<Movie> searchMovieByName(String title);
    List<Movie> searchMovieByGenre(GENRE genre);

}
