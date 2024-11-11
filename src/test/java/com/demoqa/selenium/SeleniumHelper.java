package com.demoqa.selenium;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demoqa.test.hooks.CucumberHooks;

public class SeleniumHelper {

	private WebDriver driver = CucumberHooks.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	/**
	 * @author filipesantos
	 * @param locator
	 */
    public void click (By locator) {
    	
    	WebElement obj = driver.findElement(locator);
    	click(obj);
    	
    }
    
    /**
     * @author filipesantos
     * @param locator
     */
    public void clickJS(By locator) {
    	WebElement obj = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", obj);
    }
    
    /**
     * @author filipesantos
     * @param locator
     * @return
     */
    public String getMessage(By locator) {
    	WebElement obj = driver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(obj));
        wait.until(ExpectedConditions.elementToBeClickable(obj));
    	return obj.getText();
    }
    
    /**
     * @author filipesantos
     * @param locator
     * @param value
     */
    public void sendText (By locator, String value) {
    	WebElement obj = driver.findElement(locator);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", obj);
    	wait.until(ExpectedConditions.visibilityOf(obj));
        wait.until(ExpectedConditions.elementToBeClickable(obj));
    	obj.sendKeys(value);
    	
    }
    
    /**
     * @author filipesantos
     * @param locator
     */
    public void clearText (By locator) {
    	WebElement obj = driver.findElement(locator);
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", obj);
    	wait.until(ExpectedConditions.visibilityOf(obj));
        wait.until(ExpectedConditions.elementToBeClickable(obj));
    	obj.clear();
    	
    }
    
    /**
     * @author filipesantos
     * @param locator
     * @return
     */
    public WebElement element(By locator) {
    	
    	return driver.findElement(locator);
    	
    }
    
    /**
     * @author filipesantos
     * @param locator
     * @return
     */
    public List<WebElement> elements(By locator) {
    	
    	return driver.findElements(locator);
    	
    }
	
    /**
     * @author filipesantos
     */
    public void closeDriver() {
    	
    	driver.close();
    	
    }
    
    /**
     * @author filipesantos
     * @return
     */
    public String getWindowName() {
    	return driver.getWindowHandle();
    	
    }
    
    /**
     * @author filipesantos
     * @param window
     */
    public void switchToWindow(String window) {
    	driver.switchTo().window(window);
    	
    }
    
    /**
     * @author filipesantos
     * @return
     */
    public Set<String> getWindowNames() {
    	return driver.getWindowHandles();
    	
    }

    /**
     * @author filipesantos
     * @param url
     */
	public void openPage(String url) {
		driver.navigate().to(url);
		
	}

	/**
	 * @author filipesantos
	 * @param element
	 */
	public void click(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
		
	}
	
}
