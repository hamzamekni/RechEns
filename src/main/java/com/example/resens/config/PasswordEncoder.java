package com.example.resens.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Configuration
public class PasswordEncoder {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String encode(String encodedPassword) {
        byte[] decodedBytes = Base64.getEncoder().encode(encodedPassword.getBytes());
        return new String(decodedBytes);
    }
    public static String decodePassword(String encodedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
        return new String(decodedBytes);
    }
}
