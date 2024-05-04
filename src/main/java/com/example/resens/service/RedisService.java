package com.example.resens.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisService {
    private static final String REDIS_KEY_PREFIX = "authToken:";
    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeToken(String token, String email) {
        System.out.println(token);
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + email, token);

    }
    public void removeToken(String email) {

        redisTemplate.delete(REDIS_KEY_PREFIX + email);
    }

}
