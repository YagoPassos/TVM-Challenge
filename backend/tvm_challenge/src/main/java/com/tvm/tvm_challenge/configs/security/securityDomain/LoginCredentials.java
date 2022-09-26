package com.tvm.tvm_challenge.configs.security.securityDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCredentials {
    
    public String username;
    public String password;
}
