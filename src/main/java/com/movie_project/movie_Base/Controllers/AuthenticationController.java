package com.movie_project.movie_Base.Controllers;

import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Services.AuthenticationService;
import com.movie_project.movie_Base.auth.AuthenticationResponse;
import com.movie_project.movie_Base.auth.LoginRequest;
import com.movie_project.movie_Base.auth.RegisterRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
               try {

                  return ResponseEntity.ok(authenticationService.authenticate(loginRequest));

               } catch (Exception e) {
                   AuthenticationResponse loginResponse = new AuthenticationResponse("Login Failed");
                   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
               }
            }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        try{
            return ResponseEntity.ok(authenticationService.register(request));
        }catch (Exception e){
            AuthenticationResponse response = new AuthenticationResponse("SignUp Failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout Successful");
    }

}
