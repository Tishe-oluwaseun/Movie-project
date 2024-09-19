package com.movie_project.movie_Base.Entity;

import com.movie_project.movie_Base.Enum.Badge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String email;

    private Boolean isPro;

    private Badge badge;

    @OneToOne
    private Watchlist watchlist;
    @OneToOne
    private Comment comment ;
    @OneToOne
    private Reviews reviews;





}
