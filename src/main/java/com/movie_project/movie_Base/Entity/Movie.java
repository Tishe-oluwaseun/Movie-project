package com.movie_project.movie_Base.Entity;

import com.movie_project.movie_Base.Enum.Actor;
import com.movie_project.movie_Base.Enum.Director;
import com.movie_project.movie_Base.Enum.GENRE;
import com.movie_project.movie_Base.Enum.MovieState;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity(name = "movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private Integer releaseDate;
    private Boolean upcoming;
    private String description;

    @Enumerated
    @ElementCollection
    private Set<GENRE> genre;

    private Director director;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Comment> comments;

    private boolean isPopular;//checking if the movie is in the popular section

    @Enumerated
    @ElementCollection
    private List<Actor> cast;

    private String Description;
    private String posterUrl;

    private String videoUrl;


    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<MovieState> movieState;




    private int duration; // in minutes
    private double rating; // average rating
    private int numRatings; // number of ratings

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @ManyToMany
    @JoinTable(name = "movies_watchlists", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "watchlist_id"))
    private List<Watchlist> watchlist;



}





