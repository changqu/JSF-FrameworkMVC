package com.sdi.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import alb.util.log.Log;


@ManagedBean(name = "settings")
@SessionScoped     //la alcance es toda la aplicacion
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private static final Locale CHINESE = new Locale("cn");
	private Locale locale = new Locale("es");

	public Locale getLocale() {
		// Aqui habria que cambiar algo de código para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida “es”
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log.info("Cambiando idioma a español");
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log.info("Cambiando idioma a inglés");
	}
	
	public void setChinese(ActionEvent event) { 
		locale = CHINESE;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Log.info("Cambiando idioma a chino");
	}

}
