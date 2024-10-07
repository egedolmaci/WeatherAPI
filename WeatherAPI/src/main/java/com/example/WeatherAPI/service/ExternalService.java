package com.example.WeatherAPI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WeatherAPI.model.DailyModel;

import reactor.core.publisher.Mono;

@Service
public class ExternalService {
    
    private final WebClient webClient;

    public ExternalService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/turkey?unitGroup=us&key=W9JARNCLVED6QJWZW32FEGC3P&contentType=json").build();
    }


    public Mono<DailyModel> getDailyfromAPI(String location) {
        String url = String.format("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=us&key=W9JARNCLVED6QJWZW32FEGC3P&contentType=json", location);
        
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(DailyModel.class);
    }

}
