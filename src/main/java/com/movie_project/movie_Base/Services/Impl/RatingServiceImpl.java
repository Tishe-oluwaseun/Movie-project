package com.movie_project.movie_Base.Services.Impl;

import com.movie_project.movie_Base.Entity.Movie;
import com.movie_project.movie_Base.Entity.Rating;
import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Repositories.MovieRepository;
import com.movie_project.movie_Base.Repositories.RatingRepository;
import com.movie_project.movie_Base.Repositories.UserRepository;
import com.movie_project.movie_Base.Services.RatingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;


    @Override
    public Rating addRating(Long userId, Long movieId, double ratingValue) {
       Movie movie = movieRepository.findById(movieId).orElseThrow( () -> new EntityNotFoundException("Movie Not Found with id: " + movieId));
       User user = userRepository.findById(userId).orElseThrow( () -> new EntityNotFoundException("User Not Found with id: " + userId));

       Rating rating = new Rating();
       rating.setUser(user);
       rating.setMovie(movie);
       rating.setRatingValue(ratingValue);
    log.info("rating added");
       return ratingRepository.save(rating);


    }

    @Override
    public void deleteRating(Long ratingId) {
//        Rating rating = ratingRepository.getById(ratingId);
//        ratingRepository.delete(rating);
//
//        return ratingRepository;
        log.info("deleting movie by id: {}", ratingId);
        movieRepository.deleteById(ratingId);
    }

    @Override
    public Rating updateRating(Long userId, Long movieId, double ratingValue) {
        Movie movie = movieRepository.getById(movieId);
        User user = userRepository.getById(userId);

        Rating updated = ratingRepository.getByUserAndMovie(movie, user);
        updated.setRatingValue(ratingValue);

        return ratingRepository.save(updated);
    }

    @Override
    public Rating getUserRatingOfMovie(Long userId, Long movieId) {

        Movie movie = movieRepository.getById(movieId);
        User user = userRepository.getById(userId);
        return ratingRepository.getByUserAndMovie(movie, user);
    }

    @Override
    public Double getAverageMovieRating(Long movieId) {
        ArrayList<Rating> movieRatings = (ArrayList<Rating>) findAllRatingsByMovie(movieId);
        Double totalRating = 0.0;
        int ratingCount = 0;
        double averageRating;

        for (Rating rating : movieRatings) {
            totalRating = totalRating + rating.getRatingValue();
            ratingCount++;
        }
        averageRating = totalRating / ratingCount;
        return averageRating;
    }

    @Override
    public List<Rating> findAllRatingsByMovie( Long movieId) {
        Movie movie = movieRepository.getById(movieId);
        return ratingRepository.findByMovie( movie);
    }
}
