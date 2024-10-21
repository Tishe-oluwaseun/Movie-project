package com.movie_project.movie_Base.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long Id;
    private String title;
    private Integer releaseDate;

    private String description;

    @Enumerated
    @ElementCollection
    private Set<GENRE> genre = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();



    private String posterUrl;

    private String videoUrl;



    private String movieImages;


    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<MovieState> movieState =  new ArrayList<>();


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Director director;



    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();


    @ManyToMany
    private List<Watchlist> watchlist =  new ArrayList<>();


}





