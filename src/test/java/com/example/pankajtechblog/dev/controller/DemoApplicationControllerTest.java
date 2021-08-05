package com.example.pankajtechblog.dev.controller;

import com.example.pankajtechblog.dev.model.User;
import com.example.pankajtechblog.dev.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DemoApplicationController.class})
@ExtendWith(SpringExtension.class)
public class DemoApplicationControllerTest {
    @Autowired
    private DemoApplicationController demoApplicationController;

    @MockBean(name = "userService")
    private UserService userService;

    @Test
    public void testGetUserDetail() throws Exception {
        when(this.userService.getUserDetails(anyString(), anyString())).thenReturn(new User());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{id}", "42")
                .param("delayTime", "foo");
        MockMvcBuilders.standaloneSetup(this.demoApplicationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{}"));
    }
}

