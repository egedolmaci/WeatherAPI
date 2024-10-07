package com.example.WeatherAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherAPI.model.DailyModel;
import com.example.WeatherAPI.service.ExternalService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping()
public class WeatherController {
    
    private final ExternalService externalService;
    
    public WeatherController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping
    public Mono<DailyModel> getWeather() {
        Mono<DailyModel> dailyModel = externalService.getDailyfromAPI();
        return dailyModel;
    }


}
