package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.Entity.Watchlist;

public interface WatchlistService {
    Watchlist getWatchlistByUserIdI(Long userId);
    Watchlist addMovietoWatchList(Long movieId);
    void removeMoviefromWatchlist (Long movieId);
//    check if movie IsinWatchlist
//    get all watched movies

}
