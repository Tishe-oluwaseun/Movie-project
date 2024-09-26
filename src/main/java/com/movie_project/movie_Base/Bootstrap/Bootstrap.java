package com.movie_project.movie_Base.Bootstrap;

import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Enum.Badge;
import com.movie_project.movie_Base.Enum.Role;
import com.movie_project.movie_Base.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private final UserRepository userRepository;

    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) {
        System.out.println("Bootstrap started");
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setEmail("admin@email.com");
        user.setBadge(Badge.DIAMOND);
        user.setIsPro(true);
        user.setActiveUser(true);
        user.setRole(Role.ADMIN);
        userRepository.save(user);

    }

}
