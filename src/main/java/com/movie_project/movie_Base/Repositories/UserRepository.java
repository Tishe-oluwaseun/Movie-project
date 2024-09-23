package com.movie_project.movie_Base.Repositories;

import com.movie_project.movie_Base.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
