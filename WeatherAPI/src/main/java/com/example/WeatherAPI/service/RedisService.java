package com.example.WeatherAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.WeatherAPI.model.DailyModel;

@Service
public class RedisService {

    private final RedisTemplate<String, DailyModel> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, DailyModel> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, DailyModel value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public DailyModel getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

}
