package com.movie_project.movie_Base.Controllers;

import com.movie_project.movie_Base.Entity.User;
import com.movie_project.movie_Base.Enum.Role;
import com.movie_project.movie_Base.Repositories.UserRepository;
import com.movie_project.movie_Base.Services.Impl.UserServiceImpl;
import com.movie_project.movie_Base.Services.UserService;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Slf4j
@RestController("/user")
public class UserController {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;


    @GetMapping("/allusers")
    public ResponseEntity getAllUser(User user) {
        return ResponseEntity.ok(userService.getUserByEmail(user.getEmail()));
    }
    @GetMapping("/staff")
    public ResponseEntity getAllStaff(){
        return ResponseEntity.ok(userServiceImpl.findAllUsersByRole(Role.STAFF));
    }
    @GetMapping("/{id}")
    public ResponseEntity getUserById( @PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/user")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userServiceImpl.findAllUsersByRole(Role.USER));
    }
    @GetMapping("/admin")
    public ResponseEntity getAllAdmins(){
        return ResponseEntity.ok(userServiceImpl.findAllUsersByRole(Role.ADMIN));
    }

    //adds User to Repo
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody User users){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(users));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam Long id){
        System.out.println("user deleted");
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody User users){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(users));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody User users){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(users));
    }


}
