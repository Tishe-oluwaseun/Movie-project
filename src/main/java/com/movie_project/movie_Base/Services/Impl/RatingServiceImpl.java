package com.movie_project.movie_Base.Services.impl;

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

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;




//    @Autowired
//    public RatingServiceImpl(Rating rating, RatingRepository ratingRepository, MovieRepository movieRepository, UserRepository userRepository) {
//        this.ratingRepository = ratingRepository;
//        this.movieRepository = movieRepository;
//        this.userRepository = userRepository;
//
//    }

    @Override
    public Rating addRating(Long userId, Long movieId, double ratingValue) {
       Movie movie = movieRepository.findById(movieId).orElseThrow( () -> new EntityNotFoundException("Movie Not Found with id: " + movieId));
       User user = userRepository.findById(userId).orElseThrow( () -> new EntityNotFoundException("User Not Found with id: " + userId));

       Rating rating = new Rating();
       rating.setUser(user);
       rating.setMovie(movie);
       rating.setRating(ratingValue);
       return ratingRepository.save(rating);

    }

    @Override
    public RatingRepository deleteRating(Long ratingId) {
        Rating rating = ratingRepository.getById(ratingId);
        ratingRepository.delete(rating);
        ratingRepository.save(rating);
        return ratingRepository;
    }

    @Override
    public Double getOverallRating(Long movieId) {

        return 0.0;
    }

    @Override
    public List<Rating> getAllRatingsByMovie(Movie movie) {
        return ratingRepository.getAllbymovie(movie);
    }
}
