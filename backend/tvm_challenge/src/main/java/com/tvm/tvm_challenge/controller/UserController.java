package com.tvm.tvm_challenge.controller;

import com.tvm.tvm_challenge.entity.Users;
import com.tvm.tvm_challenge.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<Users> findAllUsers(){
        List<Users> user = service.findAllUsers();
        return user;
    }

    @GetMapping("/{id}")
    public Optional<Users> findUserByd(@PathVariable Long id){
        Optional<Users> user = service.findUsersById(id);
        return user;
    }

    @PostMapping
    public void addUsers(@RequestBody Users user){
        service.addUsers(user);
    }
}
