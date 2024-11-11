package com.demoqa.steps;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.demoqa.pageobject.BrowserWindows;
import com.demoqa.selenium.SeleniumHelper;
import com.demoqa.test.hooks.CucumberHooks;

import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class BrowserWindowsSteps {

	SeleniumHelper selenium = new SeleniumHelper();
	String janela;
	
	@Então("uma nova janela é aberta com a mensagem {string}")
    public void umaNovaJanelaAbertaVerificaMensagem(String janelaAtual) throws InterruptedException {
    	Set<String> windowHandles = selenium.getWindowNames();
        for (String handle : windowHandles) {
            if (!handle.equals(janela)) {
                selenium.switchToWindow(handle);
                break;
            }
        }

        
        String messageString = selenium.getMessage(BrowserWindows.TITLE);
        assertEquals(janelaAtual, messageString);
        TimeUnit.SECONDS.sleep(1);
        CucumberHooks.screenshot();
    }

    @Então("fecho a nova janela")
    public void fechoNovaJanela() {
        selenium.closeDriver();
        selenium.switchToWindow(janela);
    }
    
    @Quando("clico no botão New Window")
    public void abrirNovaJanela() throws InterruptedException {
    	
    	janela = selenium.getWindowName();
        selenium.click(BrowserWindows.NEWWINDOW);
        TimeUnit.SECONDS.sleep(1);
    }
	
}
