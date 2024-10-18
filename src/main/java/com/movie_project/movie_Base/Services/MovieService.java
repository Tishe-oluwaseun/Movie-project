package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.DTOs.MovieDTO;
import com.movie_project.movie_Base.Entity.Director;
import com.movie_project.movie_Base.Entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    MovieDTO addMovie(Movie movie);
    Movie saveMovie(Movie movie);
    Movie updateMovie(MovieDTO movie);
    void deleteMovie(Long id);
    Movie getMovieByName(String title);
    Director saveDirector(Director director);


}
