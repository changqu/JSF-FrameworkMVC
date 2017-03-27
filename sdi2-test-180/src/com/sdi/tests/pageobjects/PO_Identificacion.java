package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Identificacion {
	public void rellenaFormulario(WebDriver driver, String pidentificador, String ppassword){
		WebElement identificador = driver.findElement(By.id("form-cuerpo:name"));
		identificador.click();
		identificador.clear();
		identificador.sendKeys(pidentificador);
		WebElement password = driver.findElement(By.id("form-cuerpo:password"));
		password.click();
		password.clear();
		password.sendKeys(ppassword);
		By boton = By.id("form-cuerpo:btLogin");
		driver.findElement(boton).click();	
	}
}
