package com.sdi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Registro {
	
	public void rellenaFormulario(WebDriver driver, String pidentificador, String pnombre, String papellidos, 
			String pcorreo, String ppassword, String prepetirPassword)
	   {
			WebElement identificador = driver.findElement(By.id("form-cuerpo:identificador"));
			identificador.click();
			identificador.clear();
			identificador.sendKeys(pidentificador);
			WebElement nombre = driver.findElement(By.id("form-cuerpo:nombre"));
			nombre.click();
			nombre.clear();
			nombre.sendKeys(pnombre);
			WebElement apellidos = driver.findElement(By.id("form-cuerpo:apellidos"));
			apellidos.click();
			apellidos.clear();
			apellidos.sendKeys(papellidos);
			WebElement correo = driver.findElement(By.id("form-cuerpo:correo"));
			correo.click();
			correo.clear();
			correo.sendKeys(pcorreo);
			WebElement password = driver.findElement(By.id("form-cuerpo:password"));
			password.click();
			password.clear();
			password.sendKeys(ppassword);
			WebElement repetirPassword = driver.findElement(By.id("form-cuerpo:repetirPassword"));
			repetirPassword.click();
			repetirPassword.clear();
			repetirPassword.sendKeys(prepetirPassword);
			//Pulsar el boton de registrarse.
			By boton = By.id("form-cuerpo:btRegistrarse");
			driver.findElement(boton).click();	   
	   }
}
