package com.movie_project.movie_Base.Services.Impl;

import com.movie_project.movie_Base.Entity.Director;
import com.movie_project.movie_Base.Entity.Movie;
import com.movie_project.movie_Base.Enum.GENRE;
import com.movie_project.movie_Base.Repositories.DirectorRepository;
import com.movie_project.movie_Base.Repositories.MovieRepository;
import com.movie_project.movie_Base.Services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    @Override
    public List<Movie> getAllMovies() {
        log.info("getting all movies");
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        log.info("getting movie by id: {}", id);
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie addMovie(Movie movie) {

        List<Movie> existingMovie = movieRepository.findByTitle(movie.getTitle());
        if (existingMovie != null) {
            log.info("Movie already exists: {}", movie.getTitle());
            return existingMovie.getFirst();
        } else {
            log.info("adding new movie: {}", movie.getTitle());
            return saveMovie(movie);
        }

    }

    @Override
    public Movie saveMovie(Movie movie) {
        log.info("saving movie: {}", movie);
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        log.info("updating movie: {}", movie);
        Movie existingMovie = getMovieById(movie.getId());
        if (existingMovie != null) {
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setReleaseDate(movie.getReleaseDate());
            existingMovie.setGenre(movie.getGenre());
            existingMovie.setDirector(movie.getDirector());
            existingMovie.setDescription(movie.getDescription());
            return saveMovie(existingMovie);
        } else {
            return null;
        }
    }

    @Override
    public void deleteMovie(Long id) {
        log.info("deleting movie by id: {}", id);
        movieRepository.deleteById(id);
    }

    @Override
    public Movie addDirector(Movie movie,Director director) {
        Director existingDirector = directorRepository.findByFirstNameAndLastName
                (director.getDirectorName());
        if (existingDirector == null) {
            existingDirector = directorRepository.save(director);
        }
        movie.setDirector(existingDirector);
        return saveMovie(movie);
    }

    @Override
    public List<Movie> searchMovieByName(String title) {
        log.info("searching movies by title: {}", title);
        return movieRepository.findByTitle(title);
    }
    @Override
    public List<Movie> searchMovieByGenre(GENRE genre) {
        log.info("searching movies by genre: {}", genre);
        return movieRepository.findByGenre(genre);
    }
}
