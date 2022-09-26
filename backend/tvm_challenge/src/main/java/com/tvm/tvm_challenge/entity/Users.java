package com.tvm.tvm_challenge.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users implements UserDetails, Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String username;
   private String password;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = (new BCryptPasswordEncoder().encode(password));
   }

   @ManyToMany
   @JoinTable(name = "Users_Roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
   private List<Roles> roles;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      
      return this.roles;
   }

   @Override
   public boolean isAccountNonExpired() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      // TODO Auto-generated method stub
      return true;
   }

   @Override
   public boolean isEnabled() {
      // TODO Auto-generated method stub
      return true;
   }

}
