package com.movie_project.movie_Base.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class DirectorDTO {
    private Long id;

    private String directorName;

    private String email;
    private String directorPicture;
}
