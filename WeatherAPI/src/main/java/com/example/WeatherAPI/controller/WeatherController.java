package com.example.WeatherAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherAPI.model.DailyModel;
import com.example.WeatherAPI.service.ExternalService;
import com.example.WeatherAPI.service.RedisService;


@RestController
@RequestMapping()
public class WeatherController {
    
    @Autowired
    private ExternalService externalService;

    @Autowired
    private RedisService redisService;

    private static final String REDIS_PREFIX = "weather:";

    @GetMapping("/dailyWorks/{location}")
    public String getWeatherWorks(@PathVariable String location) {
        return externalService.getDailyfromAPI(location).block().toString();
    }
    
    @GetMapping("/daily/{location}")
    public String getWeather(@PathVariable String location) {

        String redisKey = REDIS_PREFIX + location;

        String weatherData = redisService.getData(redisKey);
        
       if (weatherData == null) {
        System.out.println("weather data not found");
        DailyModel weatherDataObject = externalService.getDailyfromAPI(location).block();
        weatherData = weatherDataObject.toString();
        redisService.saveData(redisKey, weatherData);
       } 
       
       return weatherData;

    }



}
