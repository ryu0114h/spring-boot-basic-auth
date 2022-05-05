package com.example.springBootRestApi.controller;

import com.example.springBootRestApi.model.User;
import com.example.springBootRestApi.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        return new ResponseEntity(userService.createUser(user), HttpStatus.CREATED);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        return new ResponseEntity(userService.updateUser(id, user), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
