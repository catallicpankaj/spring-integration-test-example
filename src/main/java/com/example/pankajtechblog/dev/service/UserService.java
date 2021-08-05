package com.example.pankajtechblog.dev.service;

import com.example.pankajtechblog.dev.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service("userService")
@Slf4j
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public User getUserDetails(String id, String delayTime) throws JsonProcessingException {
        String getUserUri = UriComponentsBuilder.fromUriString("https://reqres.in/api/users/" + id + "?delay=" + delayTime)
                .build()
                .toUri().toString();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<String> userResponseEntity = restTemplate.exchange(getUserUri, HttpMethod.GET, requestEntity, String.class);
        User userData = objectMapper.readValue(userResponseEntity.getBody(), User.class);
        return userData;
    }
}
