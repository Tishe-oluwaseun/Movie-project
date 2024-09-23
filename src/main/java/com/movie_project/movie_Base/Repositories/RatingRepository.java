package com.movie_project.movie_Base.Repositories;

import com.movie_project.movie_Base.Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
