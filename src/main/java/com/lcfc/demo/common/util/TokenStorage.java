package com.lcfc.demo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TokenStorage {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * @author will
     * @param token
     */
    public void addToken(String token){
        redisTemplate.boundValueOps(token).set(token);
        redisTemplate.expire(token,12, TimeUnit.MINUTES);
    }

    /**
     * @author will
     * @param token
     * @return false 身份不对，或者身份已过期
     */
    public Boolean isTokenExsit(String token){
        if (redisTemplate.hasKey(token)) {
            return true;
        }else{
            return false;
        }
    }



}
