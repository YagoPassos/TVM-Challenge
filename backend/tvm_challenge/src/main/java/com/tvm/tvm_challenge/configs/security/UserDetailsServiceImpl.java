package com.tvm.tvm_challenge.configs.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvm.tvm_challenge.entity.Users;
import com.tvm.tvm_challenge.repository.UsersRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    final UsersRepository usersRepository;

    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username).get();
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }
 
}
