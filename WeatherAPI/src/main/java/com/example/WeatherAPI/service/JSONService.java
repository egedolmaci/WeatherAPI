package com.example.WeatherAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.WeatherAPI.model.DailyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JSONService {

    private final ObjectMapper objectMapper;

    @Autowired
    public JSONService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String convertToJson(DailyModel dailyModel) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dailyModel);
    }

}
