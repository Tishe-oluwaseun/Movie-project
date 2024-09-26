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
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

       return ratingRepository.save(rating);

    }

    @Override
    public RatingRepository deleteRating(Long ratingId) {
        Rating rating = ratingRepository.getById(ratingId);
        ratingRepository.delete(rating);

        return ratingRepository;
    }

    @Override
    public Rating updateRating(Long userId, Long movieId, double ratingValue) {
        Rating updated = ratingRepository.getbyUserIdandMovieId(userId, movieId);
        updated.setRatingValue(ratingValue);

        return ratingRepository.save(updated);
    }

    @Override
    public Rating getUserRatingOfMovie(Long userId, Long movieId) {
        return ratingRepository.getbyUserIdandMovieId(userId, movieId);
    }

    @Override
    public Double getAverageMovieRating(Long movieId) {
        ArrayList<Rating> movieRatings = (ArrayList<Rating>) getAllRatingsByMovie(movieId);
        Double totalRating = 0.0;
        int ratingCount = 0;
        double averageRating = 0.0;

        for (Rating rating : movieRatings) {
            totalRating = totalRating + rating.getRatingValue();
            ratingCount++;
        }
        averageRating = totalRating / ratingCount;
        return averageRating;
    }

    @Override
    public List<Rating> getAllRatingsByMovie( Long movieId) {

        return ratingRepository.getAllbymovieId(movieId);
    }
}
