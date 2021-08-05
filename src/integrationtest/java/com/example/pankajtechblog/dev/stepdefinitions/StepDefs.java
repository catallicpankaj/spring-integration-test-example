package com.example.pankajtechblog.dev.stepdefinitions;

import com.example.pankajtechblog.dev.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class StepDefs {

    private static final String BASE_URL = "https://reqres.in/api/users";
    RestTemplate restTemplate = new RestTemplate();

    ObjectMapper objectMapper = new ObjectMapper();
    ResponseEntity<String> response;

    @When("the client passes userId as {int}")
    public void the_client_calls_user_userId(int userId) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);
        response = restTemplate.exchange(BASE_URL + "/" + userId + "?page=1", HttpMethod.GET, requestEntity, String.class);
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(int statusCode) {
        System.out.println("statusCode is"+response.getStatusCodeValue());
        Assert.assertEquals(statusCode, response.getStatusCodeValue());
    }

    @And("the client receives user name as {word}")
    public void the_client_receives_user_name_as(String firstName) {
        User user = null;
        try {
            user = objectMapper.readValue(response.getBody(), User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(firstName, user.getData().getFirstName());
    }
}
