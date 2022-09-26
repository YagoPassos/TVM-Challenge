package com.tvm.tvm_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TvmChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TvmChallengeApplication.class, args);
    }
}
