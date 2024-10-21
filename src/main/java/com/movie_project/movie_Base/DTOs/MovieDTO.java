package com.movie_project.movie_Base.DTOs;

import com.movie_project.movie_Base.Entity.Director;
import com.movie_project.movie_Base.Enum.GENRE;
import com.movie_project.movie_Base.Enum.MovieState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {
    private Long id;
    private String title;
    private Integer releaseDate;

    private String description;
    private Set<GENRE> genre = new HashSet<>();
    private String posterUrl;
    private String videoUrl;
    private String movieImages;
    private List<MovieState> movieState =  new ArrayList<>();
    private DirectorDTO director;
}
