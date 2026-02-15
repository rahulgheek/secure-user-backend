package com.example.demo.services;

import com.example.demo.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper mapper;

    public <T> T getResponse(String key, Class<T> response){
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) {
                return null; // Return null to indicate "Cache Miss"
            }
            return mapper.readValue(o.toString(),response);
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public void setResponse(String key, Object o,Long ttl){
        try {
            String jsonValue = mapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key,jsonValue,ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
