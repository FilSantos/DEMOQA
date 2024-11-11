package com.demoqa.test.hooks;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CucumberHooks {
	
	private static WebDriver driver;
	private static Scenario scenario;
	
	@Before()
	public void startItAll(Scenario scenarioTemp) {
		scenario = scenarioTemp;
		
	}

	@After()
	public void takeScreenshotAfterScenario(Scenario scenario) {
		if (driver != null) screenshot();
		
	}
	
	@AfterAll()
	public static void beforeOrAfterAll() {
		if (driver != null) driver.quit();
		
	}
	
	public static void startSelenium() {
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
	}

	public static void screenshot() {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Evidencia");
        
	}
	
	public static Scenario getScenario() {
		return scenario;
		
	}
	
	public static WebDriver getDriver() {
		return driver;
		
	}
	
	@Dado("Abrir Chrome")
	public void abrirChrome() {
		if (driver == null) startSelenium();
		
	}
	
}
