package com.movie_project.movie_Base.Bootstrap;

import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Enum.Badge;
import com.movie_project.movie_Base.Enum.Role;
import com.movie_project.movie_Base.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    public Bootstrap(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) {
        System.out.println("Bootstrap started");
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("password1234"));
        user.setEmail("admin@gmail.com");
        user.setBadge(Badge.DIAMOND);
        user.setIsPro(true);
        user.setActiveUser(true);
        user.setRole(Role.ADMIN);
        userRepository.save(user);

    }

}
