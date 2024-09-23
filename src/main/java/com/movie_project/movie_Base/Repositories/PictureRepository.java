package com.movie_project.movie_Base.Repositories;

import com.movie_project.movie_Base.Entity.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Pictures, Long> {
}
