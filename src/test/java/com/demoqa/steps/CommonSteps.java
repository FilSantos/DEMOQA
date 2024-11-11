package com.demoqa.steps;

import org.junit.Assert;

import com.demoqa.selenium.SeleniumHelper;

import io.cucumber.java.pt.Dado;

public class CommonSteps {

	SeleniumHelper selenium = new SeleniumHelper();
	
    @Dado("acesso a página {string}")
    public void acessoPagina(String pagina) {
    	
    	String url = null;
    	switch (pagina) {
		case "DemoQA Practice Form page":
			url = "https://demoqa.com/automation-practice-form";
			break;
			
		case "DemoQA Browser Windows":
			url = "https://demoqa.com/browser-windows";
			break;
			
		case "DemoQA Web Tables":
			url = "https://demoqa.com/webtables";
			break;
			
		case "DemoQA Web Progress Bar":
			url = "https://demoqa.com/progress-bar";
			break;	
			
		default:
			Assert.fail("Página não mapeada");
			
		}
    	
    	selenium.openPage(url);
    	
    }
    
}
