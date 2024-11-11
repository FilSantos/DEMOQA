package com.demoqa.steps;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.demoqa.pageobject.ProgressBar;
import com.demoqa.selenium.SeleniumHelper;
import com.demoqa.test.hooks.CucumberHooks;

import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class ProgressBarSteps {
	
	SeleniumHelper selenium = new SeleniumHelper();
	
	@Quando("clico no botão Start")
    public void clicoBotaoStart() {
		selenium.click(ProgressBar.STARTSTOP);
    }

    @Então("paro a Progress Bar {string} de {int}%")
    public void paroProgressbar(String acao, Integer percentual) throws InterruptedException {
    	TimeUnit.MILLISECONDS.sleep(500);
    	switch (acao) {
		case "antes":
			while (true) {
	            int progress = Integer.parseInt(selenium.getMessage((ProgressBar.PROGRESSBAR)).replace("%", ""));
	            if (progress >= percentual-1) {
	                break;
	            }
	        }
			selenium.click(ProgressBar.STARTSTOP);

			break;
		case "chegar":
			while (true) {
	            int progress = Integer.parseInt(selenium.getMessage((ProgressBar.PROGRESSBAR)).replace("%", ""));
	            if (progress == percentual) {
	                break;
	            }
	        }

			break;
		default:
			Assert.fail("Página não mapeada");
		}
    	TimeUnit.MILLISECONDS.sleep(500);
    	CucumberHooks.screenshot();
    	
    }
    @Então("valido que o valor da Progress Bar é menor ou igual a {int}%")
    public void validoProgressbarMenorIgual(Integer percentual) throws InterruptedException {
        int progress = Integer.parseInt(selenium.getMessage(ProgressBar.PROGRESSBAR).replace("%", ""));
        assertTrue("O valor da progress bar não está correto!", progress <= percentual);
    }
    @Então("reseto a Progress Bar")
    public void resetoProgressbar() throws InterruptedException {
    	selenium.click(ProgressBar.RESET);
    	
    }
}
