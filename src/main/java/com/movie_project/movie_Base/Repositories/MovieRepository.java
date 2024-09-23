package com.movie_project.movie_Base.Repositories;

import com.movie_project.movie_Base.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
