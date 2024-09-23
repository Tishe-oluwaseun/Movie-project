package com.movie_project.movie_Base.Entity;

import com.movie_project.movie_Base.Enum.Badge;
import jakarta.persistence.*;
import lombok.*;

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
    private Set<Comment> comment ;

    @OneToMany(mappedBy = "user")
    private Set<Rating> reviews;

    @Lob
    private String PFP;


    public User(String name, String email, Badge badge) {
        this.username = name;
        this.email = email;
        this.badge = badge;

    }






}
