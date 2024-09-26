package com.movie_project.movie_Base.Services.Impl;

import com.movie_project.movie_Base.DTOs.MovieDTO;
import com.movie_project.movie_Base.Entity.Movie;
import com.movie_project.movie_Base.Repositories.DirectorRepository;
import com.movie_project.movie_Base.Repositories.MovieRepository;
import com.movie_project.movie_Base.Services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper = new ModelMapper();
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
    public MovieDTO addMovie(Movie movie) {
        List<Movie> existingMovie = movieRepository.findByTitle(movie.getTitle());
        if (existingMovie != null) {
            log.info("Movie already exists: {}", movie.getTitle());
            return modelMapper.map(existingMovie.getFirst(), MovieDTO.class);
        } else {
            if (movie.getDirector() != null) {
                directorRepository.findById(movie.getDirector().getId()).ifPresent(movie::setDirector);
            }
            Movie savedMovie = movieRepository.save(movie);
            return modelMapper.map(savedMovie, MovieDTO.class);
        }
    }

    @Override
    public Movie saveMovie(Movie movie) {
        log.info("saving movie: {}", movie);
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(MovieDTO movie) {
        log.info("updating movie: {}", movie);
        Movie existingMovie = movieRepository.findById(movie.getId()).orElse(null);
        if (existingMovie == null) {
            log.info("Movie not found: {}", movie.getId());
            return null;
        }
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setReleaseDate(movie.getReleaseDate());
            existingMovie.setGenre(movie.getGenre());
            existingMovie.setDirector(movie.getDirector());
            existingMovie.setDescription(movie.getDescription());
            return saveMovie(existingMovie);
        }


    @Override
    public void deleteMovie(Long id) {
        log.info("deleting movie by id: {}", id);
        movieRepository.deleteById(id);
    }



    @Override
    public Movie getMovieByName(String title) {
        List<Movie> movies = movieRepository.findByTitle(title);
        if (movies.isEmpty()) {
            return null;
        }
        return movies.getFirst();
    }

}
