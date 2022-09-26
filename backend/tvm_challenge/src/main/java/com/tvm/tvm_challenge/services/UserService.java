package com.tvm.tvm_challenge.services;

import com.tvm.tvm_challenge.entity.Users;
import com.tvm.tvm_challenge.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UsersRepository repository;

    public UserService(UsersRepository repository) {
        this.repository = repository;
    }
    public Users addUsers (Users user) {
        return repository.save(user);
    }
    public List<Users> findAllUsers() {
        return repository.findAll();
    }
    public Optional<Users> findUsersById(Long id){
        Optional<Users> user = repository.findById(id);
        return user;
    }

    public Users findUsersByUsername(String username){
       
        return repository.findByUsername(username)
        .orElseThrow(()-> new EntityNotFoundException("User " + username + " not found"));
        
    }
}
