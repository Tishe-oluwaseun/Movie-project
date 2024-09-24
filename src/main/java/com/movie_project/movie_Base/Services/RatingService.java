package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.Entity.Movie;
import com.movie_project.movie_Base.Entity.Rating;
import com.movie_project.movie_Base.Repositories.RatingRepository;

import java.util.List;

public interface RatingService {

    Rating addRating(Long userId, Long movieId, double rating);
    RatingRepository deleteRating(Long ratingId);
    //Rating getUserRating(Long userId, Long movieId);
    Double getOverallRating(Long movieId);
    List<Rating> getAllRatingsByMovie(Movie movie);
}
