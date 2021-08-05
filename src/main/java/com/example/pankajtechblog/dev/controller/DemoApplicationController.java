package com.example.pankajtechblog.dev.controller;

import com.example.pankajtechblog.dev.model.User;
import com.example.pankajtechblog.dev.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoApplicationController {
    @Autowired
    @Qualifier("userService")
    UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserDetail(@PathVariable(name = "id") String id,
                                              @RequestParam(name = "delayTime") String delayTime) throws JsonProcessingException {
        log.info("Getting UserDetail for Id-{}", id);
        User userResponse = userService.getUserDetails(id, delayTime);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
