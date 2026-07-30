package com.example.demo.service;

import com.example.demo.api.response.WeatherResponse;
import com.example.demo.cache.AppCache;
import com.example.demo.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class WeatherService {
    @Value("${weatherstack.api.key}")
    private String apiKey;

    //private static final String API="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

public WeatherResponse getWeather(String city) {

    String finalAPI = appCache.APP_CACHE.get("weather_api")
            .replace("<city>", city)
            .replace("<api_key>", apiKey);
    System.out.println("API Key = " + apiKey);

    ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
    WeatherResponse body = response.getBody();
    ResponseEntity<String> response1 = restTemplate.getForEntity(finalAPI, String.class);

    System.out.println(response1.getBody());
    System.out.println("API Key = " + apiKey);
    System.out.println(finalAPI);
    return body;
}

}
