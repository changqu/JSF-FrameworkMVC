package com.sdi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_RegViaje {
	
	List<WebElement> elementos = null;
//	List<WebElement> elementosClass = null;
	
	public void rellenaFormulario(WebDriver driver) throws InterruptedException{
		WebElement viajeDefecto = driver.findElement(By.id("form-cuerpo:viajeDefecto"));
		viajeDefecto.click();
		Thread.sleep(1000);
		//otro click
		viajeDefecto = driver.findElement(By.id("form-cuerpo:viajeDefecto"));
		viajeDefecto.click();
		Thread.sleep(1000);
		//tercer click Marcarlo otra vez
		viajeDefecto = driver.findElement(By.id("form-cuerpo:viajeDefecto"));
		viajeDefecto.click();
		Thread.sleep(1000);
		//Pulsar el boton de registrarse.
		By boton = By.id("form-cuerpo:btRegistrarViaje");
		driver.findElement(boton).click();	   
    }
	
	public void rellenaFormularioInvalido(WebDriver driver, String plazaLibre){
		WebElement viajeDefecto = driver.findElement(By.id("form-cuerpo:viajeDefecto"));
		viajeDefecto.click();//aqui el viaje defecto es una accion de primefaces
		//recoge todos los elementos del plaza libre
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "plazaLibre", 10);
		elementos.get(0).click();
		//elementos.get(0).clear();//porque no puede limpiar valor?
		Actions builder = new Actions(driver);	    
		builder.sendKeys(plazaLibre).perform(); 
		//Pulsar el boton de registrarse.
		By boton = By.id("form-cuerpo:btRegistrarViaje");
		driver.findElement(boton).click();	   
    }
	
	public void rellenaCiudadProvincia(WebDriver driver) throws InterruptedException{
		Actions builder = new Actions(driver);	
		//salidaCiudad
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "salidaCiudad", 10);
		elementos.get(0).click();
		builder.sendKeys("O").perform(); 
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-autocomplete-item", 10);
		builder.moveToElement(elementos.get(1)).perform();//move cursor al primer elemento
		elementos.get(1).click();//hacer click
		Thread.sleep(500);
		
		//llegadaCiudad
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "destinoCiudad", 10);
		elementos.get(0).click();
		builder.sendKeys("B").perform(); 
		Thread.sleep(1000);
//		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-autocomplete-item", 10);
//		builder.moveToElement(elementos.get(1)).perform();
//		elementos.get(1).click();
		//salidaProvincia	
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "salidaProvincia", 10);
		elementos.get(0).click();    
		builder.sendKeys("A").perform(); 
		Thread.sleep(1000);
//		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-autocomplete-item", 10);
//		builder.moveToElement(elementos.get(2)).perform();
//		elementos.get(2).click();
		//llegadaProvincia
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "destinoProvincia", 10);
		elementos.get(0).click();
		builder.sendKeys("C").perform(); 
		Thread.sleep(1000);
//		elementos = SeleniumUtils.EsperaCargaPagina(driver, "class", "ui-autocomplete-item", 10);
//		builder.moveToElement(elementos.get(1)).perform();
//		elementos.get(1).click();
		
		//Pulsar el boton de registrarse.
		By boton = By.id("form-cuerpo:btRegistrarViaje");
		driver.findElement(boton).click();	
	}
	
}
