package com.tvm.tvm_challenge.repository;

import org.springframework.stereotype.Repository;

import com.tvm.tvm_challenge.entity.Roles;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    
}
