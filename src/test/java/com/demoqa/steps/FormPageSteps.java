package com.demoqa.steps;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import com.demoqa.pageobject.FormPage;
import com.demoqa.selenium.SeleniumHelper;
import com.demoqa.test.hooks.CucumberHooks;
import com.github.javafaker.Faker;

import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class FormPageSteps {
	
	SeleniumHelper selenium = new SeleniumHelper();

	@Quando("preencho formulário com dados valores aleatorios")
    public void preenchoFormuDadosAleatorios() throws InterruptedException {
    	@SuppressWarnings("deprecation")
		Faker faker = new Faker(new Locale("pt-BR"));
    	Faker fakerMail = new Faker();
    	
        int r = (int) (Math.random()*3);
        String genderString = new String [] {"Male","Female","Other",}[r];
        
        r = (int) (Math.random()*3);
        String hobbiesString = new String [] {"Sports","Reading","Music",}[r];
        
        selenium.sendText(FormPage.FIRSTNAME, faker.address().firstName());
        selenium.sendText(FormPage.LASTNAME, faker.address().lastName());
        selenium.sendText(FormPage.EMAIL, fakerMail.internet().emailAddress());

        List<WebElement> hobbies = selenium.elements(FormPage.HOBBIES);
        for (WebElement hobbiesItem : hobbies) {
			if (hobbiesItem.getText().equals(hobbiesString)) {
				selenium.click(hobbiesItem);
				break;
			}
		}
        
        List<WebElement> gender = selenium.elements(FormPage.GENDER);
        for (WebElement genderItem : gender) {
        	if (genderItem.getText().equals(genderString)) {
				selenium.click(genderItem);
				break;
			}
		}
        
        selenium.sendText(FormPage.SUBJECT, "a");

        List<WebElement> subjectOpcoes = selenium.elements(FormPage.SUBJECTOPTIONS);
        selenium.click(subjectOpcoes.get((int) (Math.random()*subjectOpcoes.size())));
        
        selenium.sendText(FormPage.PHONE, faker.phoneNumber().cellPhone().replaceAll("\\D", ""));
        selenium.sendText(FormPage.ADDRESS, faker.address().fullAddress());
        
        selenium.click(FormPage.STATE);
        List<WebElement> stateOptions = selenium.elements(FormPage.STATEOPTIONS);
        selenium.click(stateOptions.get(((int) (Math.random()* stateOptions.size()))));

        selenium.click(FormPage.CITY);
        List<WebElement> cityOptions = selenium.elements(FormPage.CITYOPTIONS);
        selenium.click(cityOptions.get(((int) (Math.random()* cityOptions.size()))));
       
    }

    @Quando("realizo upload de arquivo")
    public void uploadArquivo() {
        selenium.sendText(FormPage.UPLOADDOCUMENT, System.getProperty("user.dir") + "/src/test/resources/sample.txt");
        
    }

    @Quando("envio o formulário")
    public void envioForm() {
    	CucumberHooks.screenshot();
    	selenium.click(FormPage.SUBMIT);
        
    }

    @Então("é exibido uma página de confirmação de cadastro")
    public void exibeConfirmacao() throws InterruptedException {
        assertTrue("Popup confirmation is not displayed.", selenium.element(FormPage.MODAL).isDisplayed());
        TimeUnit.SECONDS.sleep(1);
        CucumberHooks.screenshot();
        
    }

    @Então("fecho o popup")
    public void fechaPopup() {
        selenium.clickJS(FormPage.CLOSEMODAL);
        
    }
    
}
