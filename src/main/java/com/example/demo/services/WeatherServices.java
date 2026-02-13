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

    @Autowired
    private RedisService service;

    public WeatherResponse.MainData getWeather(String city){
        WeatherResponse response = service.getResponse("weather_of_" + city,WeatherResponse.class);
        if(response != null){
            return response.main;
        }
        else{
            String finalApi = uri.replace("API_KEY",API_KEY).replace("CITY",city);
            ResponseEntity<WeatherResponse> body = restTemplate.exchange(finalApi, HttpMethod.GET,null,WeatherResponse.class);
            if(body.getBody() != null){
                service.setResponse("weather_of_" + city,body.getBody(),300l);
            }
            return body.getBody().main;
        }

    }

}
