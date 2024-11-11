package com.demoqa.test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.demoqa",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class Test  {
}