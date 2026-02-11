package com.example.demo.services;

import com.example.demo.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServices {
    @Value("${weather.api.key}")
    private String API_KEY;
    String uri = "https://api.openweathermap.org/data/2.5/weather?&appid=API_KEY&units=metric&q=CITY";
    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse.MainData getWeather(String city){
        String finalApi = uri.replace("API_KEY",API_KEY).replace("CITY",city);
        System.out.println(city);
        ResponseEntity<WeatherResponse> body = restTemplate.exchange(finalApi, HttpMethod.GET,null,WeatherResponse.class);
        return body.getBody().main;
    }

}
