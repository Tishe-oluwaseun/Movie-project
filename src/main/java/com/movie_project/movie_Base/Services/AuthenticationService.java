package com.movie_project.movie_Base.Services;

import com.movie_project.movie_Base.Entity.User;

public interface AuthenticationService {

    User login(String username, String password) ;
    void signup(User user);

}
