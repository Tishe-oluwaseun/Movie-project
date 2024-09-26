package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.DTOs.UserDTO;
import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Enum.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    User addUser(User user);
    User saveUser(User user);
    void deleteUser(Long id);
    UserDTO updateUser(User user);


}
