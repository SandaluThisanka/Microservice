package com.User.UserService.Controller;

import com.User.UserService.Model.UserEntity;
import com.User.UserService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<UserEntity> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getuser/{id}")
    public UserEntity getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/newuser")
    public void addUser(@RequestBody UserEntity user){
        userService.SaveUser(user);
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.DeleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserEntity user) {
        if (userService.authenticateUser(user.getEmail(), user.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful!");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password!");
        }
    }

}
