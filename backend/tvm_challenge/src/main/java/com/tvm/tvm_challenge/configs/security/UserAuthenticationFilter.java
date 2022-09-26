package com.tvm.tvm_challenge.configs.security;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvm.tvm_challenge.configs.security.securityDomain.LoginCredentials;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            BufferedReader reader = request.getReader();
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            LoginCredentials authRequest = objectMapper.readValue(sb.toString(), LoginCredentials.class);
           
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authRequest.username, authRequest.password);

            setDetails(request, authenticationToken);

            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
