package com.movie_project.movie_Base.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
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
    private User user;

    @ManyToOne
    private Movie movies;



}
