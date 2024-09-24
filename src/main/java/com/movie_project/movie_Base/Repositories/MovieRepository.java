package com.movie_project.movie_Base.Repositories;


import com.movie_project.movie_Base.Entity.Movie;
import com.movie_project.movie_Base.Enum.GENRE;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(/*@Param("genre")*/ GENRE genre);

}
