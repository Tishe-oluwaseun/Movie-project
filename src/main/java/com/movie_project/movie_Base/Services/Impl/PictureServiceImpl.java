package com.movie_project.movie_Base.Services.Impl;

import com.movie_project.movie_Base.Entity.Pictures;
import com.movie_project.movie_Base.Repositories.PictureRepository;
import com.movie_project.movie_Base.Services.PictureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    @Override
    public void save(Pictures pictures) {pictureRepository.save(pictures);}

    @Override
    public void deleteById(Long id) {pictureRepository.deleteById(id);}
}
