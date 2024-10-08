package com.example.WeatherAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherAPI.model.DailyModel;
import com.example.WeatherAPI.service.ExternalService;
import com.example.WeatherAPI.service.JSONService;
import com.example.WeatherAPI.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;


@RestController
@RequestMapping()
public class WeatherController {
    
    @Autowired
    private ExternalService externalService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private JSONService jsonService;

    private static final String REDIS_PREFIX = "weather:";

    @GetMapping("/dailyWorks/{location}")
    public String getWeatherWorks(@PathVariable String location) {
        return externalService.getDailyfromAPI(location).block().toString();
    }
    
    @GetMapping("/daily/{location}")
    public String getWeather(@PathVariable String location) throws JsonProcessingException {

        String redisKey = REDIS_PREFIX + location;

        DailyModel weatherData = redisService.getData(redisKey);
        
       if (weatherData == null) {
        System.out.println("weather data not found");
        weatherData = externalService.getDailyfromAPI(location).block();
        redisService.saveData(redisKey, weatherData) ;
       } 

       try {
       String weather = jsonService.convertToJson(weatherData);
       return weather;

       } catch(Error e) {
        return "Error";

       }
       

    }



}
