package com.sdi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_ViajeAcept { 
	
	public void solicitarPlaza(WebDriver driver){
		By boton = By.id("form-cuerpo:btSolicitarPlaza");
		driver.findElement(boton).click();	   
    }
	
	public void confirmarPasajero(WebDriver driver, List<WebElement> elementos){
		elementos.get(0).click();	   
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Se ha confirmado pasajero correctamente", 10);
    }
	
	public void confirmarPasajeroDosPersona(WebDriver driver, List<WebElement> elementos){
		elementos.get(0).click();	
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Se ha confirmado pasajero correctamente", 10);
		
		elementos=SeleniumUtils.EsperaCargaPagina(driver, "id", "btConfirmarPasajero", 10);
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Se ha confirmado pasajero correctamente", 10);
    }
	
	//no va???????????
//	public void confirmarPasajeroTresPersona(WebDriver driver, List<WebElement> elementos){
//		elementos.get(0).click();
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Se ha confirmado pasajero correctamente", 10);
//		
//		elementos=SeleniumUtils.EsperaCargaPagina(driver, "id", "btConfirmarPasajero", 10);
//		elementos.get(0).click();
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "Se ha confirmado pasajero correctamente", 10);
//		
//		elementos=SeleniumUtils.EsperaCargaPagina(driver, "id", "btConfirmarPasajero", 10);
//		elementos.get(0).click();
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "No se puede confirmar pasajero dado no hay plaza", 10);
//    }
	
}
