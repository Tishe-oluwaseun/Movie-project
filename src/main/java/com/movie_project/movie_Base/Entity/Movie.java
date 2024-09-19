package com.movie_project.movie_Base.Entity;

import com.movie_project.movie_Base.Enum.Actor;
import com.movie_project.movie_Base.Enum.Director;
import com.movie_project.movie_Base.Enum.GENRE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String movieName;

    private Integer releaseDate;

    private Boolean upcoming;


    @Enumerated
    @ElementCollection
    private Set<GENRE> genre;

    private Director director;

    @OneToMany
    private List <Comment> comments;

    private boolean isPopular;

    @Enumerated
    @ElementCollection
    private Set<Actor> actor;

    private String Description;
    private String imageUrl;

    private String videoUrl;







}
