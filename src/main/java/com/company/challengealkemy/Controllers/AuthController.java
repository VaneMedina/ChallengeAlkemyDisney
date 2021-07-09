package com.company.challengealkemy.Controllers;

import com.company.challengealkemy.Dto.LoginDto;
import com.company.challengealkemy.Model.User;
import com.company.challengealkemy.Servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't register user.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(loginDto));
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect credentials.");
        }
    }

}
