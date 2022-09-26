package com.tvm.tvm_challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvm.tvm_challenge.entity.Roles;
import com.tvm.tvm_challenge.repository.RolesRepository;

@Service
public class RolesService {
    @Autowired
    RolesRepository repository;

    public RolesService(RolesRepository repository) {
        this.repository = repository;
    }

    public List<Roles> findAllRoles() {
        return repository.findAll();
    }

    public Optional<Roles> findRoleById(Long id){
            Optional<Roles> role = repository.findById(id);
        return role;
    }

    public Roles addRole (Roles roles){
        return repository.save(roles);
    }
}
