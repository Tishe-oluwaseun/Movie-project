package com.movie_project.movie_Base.Repositories;


import com.movie_project.movie_Base.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> getAllbymovieId(Long movieId);

    Rating getbyUserIdandMovieId(Long userId, Long movieId);
}
