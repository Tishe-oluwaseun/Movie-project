package com.movie_project.movie_Base.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long actorId;

    String actorName;

    String actorPicture;
}
