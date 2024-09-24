package com.movie_project.movie_Base.Entity;

import com.movie_project.movie_Base.Enum.Badge;
import com.movie_project.movie_Base.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String username;
    private String password;
    private String email;

    private Boolean isPro;

    @Enumerated(EnumType.STRING)
    private Badge badge;

    @OneToOne
    private Watchlist watchlist;

    private boolean isActiveUser = true;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comment = new HashSet<>(); ;

    @OneToMany(mappedBy = "user")
    private Set<Rating> reviews = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Movie> movies = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Lob
    private String Picture;


    public User(String name, String email, Badge badge, Role role) {
        this.username = name;
        this.email = email;
        this.badge = badge;
        this.role = role;
    }






}
