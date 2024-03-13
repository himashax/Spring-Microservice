package com.assignment.authenticationservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/msg")
    public String msg() {
        return "hi key";
    }

    @GetMapping("/secret")
    public String secretkey() {
        return "secret key";
    }

    @GetMapping("")
    public List<User> users() {
        return userService.getUsers();
    }


}
