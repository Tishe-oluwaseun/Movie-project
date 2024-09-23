package com.movie_project.movie_Base.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    @Temporal(TemporalType.DATE)
    private Date commentDate;
}
