package com.example.libraryrest.controller;

import com.example.libraryrest.model.User;
import com.example.libraryrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> allUsers(){return userService.allUsers();}

    @PostMapping
    public User createUser(@RequestBody User user) { return userService.addUser(user); }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) { userService.deleteUser(id); }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PostMapping("/{userId}/borrow/{bookId}")
    public String borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return userService.borrowBook(userId, bookId);
    }

    @PostMapping("/{userId}/return/{bookId}")
    public String returnBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return userService.returnBook(userId, bookId);
    }
}
