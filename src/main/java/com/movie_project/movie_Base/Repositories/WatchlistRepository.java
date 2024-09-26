package com.movie_project.movie_Base.Repositories;


import com.movie_project.movie_Base.Entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;



public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
}
