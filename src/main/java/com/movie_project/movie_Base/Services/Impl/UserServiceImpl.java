package com.movie_project.movie_Base.Services.Impl;

import com.movie_project.movie_Base.DTOs.UserDTO;
import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Enum.Role;
import com.movie_project.movie_Base.Repositories.UserRepository;
import com.movie_project.movie_Base.Services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private ModelMapper mapper;



    @Override
    public User getUserById(Long id) {
        log.info("getUserById");
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        log.info("gettingUserByEmail");
        return userRepository.findUsersByEmail(email);
    }

    @Override
    public User addUser(User user) {
        log.info("addUser");
        Optional<User> oldUser = userRepository.findUsersByEmail(user.getEmail());
        if (oldUser.isEmpty()) { //if the user does not exist it creates a new user
            user.setRole(Role.USER);
            saveUser(user);
            return user;
        } else {
            return oldUser.get();}
    }

    @Override
    public User saveUser(User user) {
        log.info("saveUser");
        return userRepository.save(user);
    }
    @Override
    public UserDTO updateUser(User user) {
        // Retrieve the existing user from the database
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            log.info("User not found: {}", user.getId());
            return null;
        }
        // Update the existing user with the new information
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());


        // Saves the updated user
        User savedUser = userRepository.save(existingUser);

        // Returns the updated user as a UserDTO
        return mapper.map(savedUser, UserDTO.class);
    }



    public ArrayList<UserDTO> findAllUsersByRole(Role role){
        ArrayList<UserDTO> userList = new ArrayList<>();
        for (User user: userRepository.findAllByRole(role)
        ) {
            userList.add(mapper.map(user, UserDTO.class));
        }return  userList;}


    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser");
        userRepository.deleteById(id);
    }


    }
