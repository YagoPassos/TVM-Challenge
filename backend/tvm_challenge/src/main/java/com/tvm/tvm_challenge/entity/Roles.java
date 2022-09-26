package com.tvm.tvm_challenge.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import com.tvm.tvm_challenge.enums.RoleName;

@Entity
@Table(name = "Roles")
public class Roles implements GrantedAuthority, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName roleName;


    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
