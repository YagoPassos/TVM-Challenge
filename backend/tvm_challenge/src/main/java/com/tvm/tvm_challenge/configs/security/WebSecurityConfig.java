package com.tvm.tvm_challenge.configs.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.Value;

@Configuration
public class WebSecurityConfig {

    @Autowired
    AuthSucessHandler authSucessHandler;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests((auth) -> {
                    try {
                        auth
                                .antMatchers(HttpMethod.GET, "/clients/**").permitAll()
                                .antMatchers(HttpMethod.GET, "/users").hasRole("USER")
                                .antMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")
                                .anyRequest().authenticated()
                                .and()
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                // ;
                                .and()
                                .addFilter(authenticationFilter())
                                .addFilter(
                                        new JwtAuthorizationFilter(authenticationManager, userDetailsService,
                                                SecurityConstants.SECRET))
                                .exceptionHandling()
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                // .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserAuthenticationFilter authenticationFilter() throws Exception {
        UserAuthenticationFilter filter = new UserAuthenticationFilter();

        filter.setAuthenticationSuccessHandler(authSucessHandler);
        filter.setAuthenticationManager(authenticationManager);

        return filter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}


