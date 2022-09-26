package com.tvm.tvm_challenge.configs.security;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tvm.tvm_challenge.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                        Authentication authentication) throws IOException, ServletException {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();


                String token = JWT.create()
                                .withSubject(userDetails.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXP_TIME))
                                .sign(Algorithm.HMAC256(SecurityConstants.SECRET));

                response.addHeader("Authorization", "Bearer " + token);
                response.getWriter().write("{\"token\":\"" + token + "\"}");
                response.flushBuffer();

                super.onAuthenticationSuccess(request, response, authentication);
        }

}
