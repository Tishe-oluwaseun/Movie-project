package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.Entity.Rating;
import com.movie_project.movie_Base.Repositories.RatingRepository;

import java.util.List;

public interface RatingService {

    Rating addRating(Long userId, Long movieId, double rating);
    RatingRepository deleteRating(Long ratingId);
    Rating updateRating(Long userId, Long movieId, double rating);
    Rating getUserRatingOfMovie(Long userId, Long movieId);
    Double getAverageMovieRating(Long movieId);
    List<Rating> findAllRatingsByMovie( Long movieId);
}
