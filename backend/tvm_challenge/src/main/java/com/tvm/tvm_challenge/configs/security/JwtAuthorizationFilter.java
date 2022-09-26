package com.tvm.tvm_challenge.configs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tvm.tvm_challenge.services.UserService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private UserDetailsServiceImpl userService;
    private String secret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userService,
            String secret) {
        super(authenticationManager);
        this.userService = userService;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        UsernamePasswordAuthenticationToken auth = getAuthentication(request);

        if (auth == null) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return null;
        }

        String username = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET))
                .build()
                .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getSubject();

        if (username == null) {
            return null;
        }

        UserDetails users = userService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(users.getUsername(), null, users.getAuthorities());
    }

}
