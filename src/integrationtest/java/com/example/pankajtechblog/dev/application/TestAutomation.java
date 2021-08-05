package com.example.pankajtechblog.dev.application;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@Category(IntegrationTest.class)
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/integrationtest/resources/features" , glue = "com.example.pankajtechblog.dev.stepdefinitions")
public class TestAutomation {
}
