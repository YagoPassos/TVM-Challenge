package com.tvm.tvm_challenge.controller;

import com.tvm.tvm_challenge.entity.Roles;
import com.tvm.tvm_challenge.services.RolesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/roles")
public class RoleController {
    
    private RolesService service;
    
    public RoleController(RolesService service) {
        this.service = service;
    }

    @GetMapping
    public List<Roles> findAllRoles(){
        List<Roles> Roles = service.findAllRoles();
        return Roles;
    }
    @GetMapping("/{id}")
    public Optional<Roles> findRoleById(@PathVariable Long id){
        Optional<Roles> role = service.findRoleById(id);
        return role;
    }

    @PostMapping
    public void addRole(@RequestBody Roles Roles){
        service.addRole(Roles);
    }
}
