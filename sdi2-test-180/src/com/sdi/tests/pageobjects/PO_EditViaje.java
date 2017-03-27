package com.sdi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sdi.tests.utils.SeleniumUtils;

public class PO_EditViaje {
	
	List<WebElement> elementos = null;
	
	public void rellenaFormulario(WebDriver driver, String comentario, String salidaCodigoPostal){
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "comentario", 10);
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).sendKeys(comentario);
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "salidaCodigoPostal", 10);
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).sendKeys(salidaCodigoPostal);
		//Pulsar el boton de registrarse.
		By boton = By.id("form-cuerpo:btModificarViaje");
		driver.findElement(boton).click();	   
    }
	
	public void rellenaFormularioInvalidoPlazaLibre(WebDriver driver, String plazaLibre){
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "plazaLibre", 10);
		elementos.get(0).click();
		elementos.get(0).clear();
		elementos.get(0).sendKeys(plazaLibre);
		//Pulsar el boton de registrarse.
		By boton = By.id("form-cuerpo:btModificarViaje");
		driver.findElement(boton).click();	 
    }
	
	
}
