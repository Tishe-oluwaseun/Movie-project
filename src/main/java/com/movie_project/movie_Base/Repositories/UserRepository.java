package com.movie_project.movie_Base.Repositories;

import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Enum.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByEmail(String email);

    ArrayList<User> findAllByRole(Role role);
}
