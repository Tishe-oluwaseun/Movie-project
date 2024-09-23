package com.movie_project.movie_Base.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "watchlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Boolean hasWatchedMovie = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movies;



}
