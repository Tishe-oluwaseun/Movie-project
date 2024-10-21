package com.movie_project.movie_Base.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "comment cannot be empty")
    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    @Temporal(TemporalType.DATE)
    private Date commentDate;
}
