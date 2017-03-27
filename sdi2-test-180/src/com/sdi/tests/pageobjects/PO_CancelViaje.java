package com.sdi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_CancelViaje {
	
	public void cancelarUnViaje(WebDriver driver, 
			List<WebElement> botonCancelarViaje, List<WebElement> viajes){
		viajes.get(4).click();
		botonCancelarViaje.get(0).click();
	}

	public void cancelarMulViaje(WebDriver driver, 
			List<WebElement> botonCancelarViaje, List<WebElement> viajes){
		viajes.get(6).click();//los select salta de 2 en 2: 0 2 4 6 8 
		viajes.get(8).click();
		botonCancelarViaje.get(0).click();
	}

}
