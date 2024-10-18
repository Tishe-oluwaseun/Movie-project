package com.movie_project.movie_Base.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "directors")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private String directorName;

    private String email;

    @OneToMany(mappedBy = "director")
    private Set<Movie> movie = new HashSet<>();

    private String directorPicture;


}
