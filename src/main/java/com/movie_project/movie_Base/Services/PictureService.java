package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.Entity.Pictures;

public interface PictureService {

    void save(Pictures pictures);


    public void deleteById(Long id);
}
