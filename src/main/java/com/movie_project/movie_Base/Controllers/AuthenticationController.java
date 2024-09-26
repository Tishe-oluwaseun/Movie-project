package com.movie_project.movie_Base.Controllers;

import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Services.AuthenticationService;
import com.movie_project.movie_Base.dto.LoginRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
               try {
                   User user = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
                   session.setAttribute("user", user);
                   return ResponseEntity.ok("Login Successful");
               } catch (Exception e) {
                   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed: " + e.getMessage());
               }

    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user){
        try{
            authenticationService.signup(user);
            return ResponseEntity.ok("Signup Successful");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("SignUp Failed");
        }
    }


    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout Successful");
    }

}
