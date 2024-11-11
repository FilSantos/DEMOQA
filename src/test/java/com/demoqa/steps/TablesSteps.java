package com.demoqa.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import com.demoqa.pageobject.Tables;
import com.demoqa.selenium.SeleniumHelper;
import com.demoqa.test.hooks.CucumberHooks;
import com.github.javafaker.Faker;

import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class TablesSteps {
	
	SeleniumHelper selenium = new SeleniumHelper();
	
	List<String> listaNomesWebForm = new ArrayList<>();
	
    Integer registrosWebForms = 12;
    Integer registrosWebFormsAtual;
	
	@Quando("crio registro com dados valores aleatorios")
    public void crioRegistro() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        CucumberHooks.screenshot();
    	Random random = new Random();
    	
    	registrosWebFormsAtual = selenium.elements(Tables.EDIT).size();
    	
    	for (int i = 1; i < registrosWebForms; i++) {
    		
        	@SuppressWarnings("deprecation")
			Faker faker = new Faker(new Locale("pt-BR"));
        	Faker fakerMail = new Faker();
        	
            int r = (int) (Math.random()*3);
            String departamento = new String [] {"Comercial","Financeiro","Tecnologia",}[r];
            int salario = (int) (Math.random()*18000);
            
            int idade = random.nextInt(65 - 18) + 18;
    		
        	selenium.click(Tables.NEW);
        	
        	String primeiroNome = faker.address().firstName();
        	selenium.sendText(Tables.FIRSTNAME, primeiroNome);
        	selenium.sendText(Tables.LASTNAME, faker.address().lastName());
        	selenium.sendText(Tables.EMAIL, fakerMail.internet().emailAddress());
        	selenium.sendText(Tables.AGE, String.valueOf(idade));
        	selenium.sendText(Tables.SALARY, String.valueOf(salario));
        	selenium.sendText(Tables.DEPARTAMENT, departamento);
        	
        	selenium.click(Tables.SUBMIT);

            listaNomesWebForm.add(primeiroNome);
            TimeUnit.SECONDS.sleep(1);
            CucumberHooks.screenshot();
		}
        TimeUnit.SECONDS.sleep(1);
        CucumberHooks.screenshot();

    }

    @Quando("altero registro com dados valores aleatorios e registro é editado com sucesso")
    public void alteroRegistro() throws InterruptedException {
        
    	TimeUnit.SECONDS.sleep(1);
        CucumberHooks.screenshot();
        
    	List<WebElement> registros = selenium.elements(Tables.EDIT);
    	selenium.click(registros.get(registrosWebFormsAtual-1));
    	@SuppressWarnings("deprecation")
		Faker faker = new Faker(new Locale("pt-BR"));
    	selenium.clearText(Tables.LASTNAME);
    	selenium.sendText(Tables.LASTNAME, faker.address().lastName());
    	selenium.click(Tables.SUBMIT);
    	TimeUnit.SECONDS.sleep(1);
        CucumberHooks.screenshot();
    }

    @Então("apago todos os registros")
    public void apagoRegistros() {
    	
    	for (int i = 0; i < registrosWebForms-1; i++) {
    		List<WebElement> deleteTableItems = selenium.elements(Tables.DELETE);
    		selenium.click(deleteTableItems.get(registrosWebFormsAtual));
		}
    	
    }
	
}
