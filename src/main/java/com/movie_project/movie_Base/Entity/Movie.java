package com.movie_project.movie_Base.Entity;


import com.movie_project.movie_Base.Enum.GENRE;
import com.movie_project.movie_Base.Enum.MovieState;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
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
    //private Boolean upcoming;
    private Boolean upcoming;

    private String description;

    @Enumerated
    @ElementCollection
    private Set<GENRE> genre = new HashSet<>();



    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();


    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Actor> cast;

    private String posterUrl;

    private String videoUrl;



    private String movieImages;


    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<MovieState> movieState =  new ArrayList<>();

    @ManyToOne
    private Director director;



    private int duration; // in minutes


    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @ManyToMany
    private List<Watchlist> watchlist =  new ArrayList<>();


}





