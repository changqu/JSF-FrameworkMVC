package com.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "ciudadProvincia")                    
@SessionScoped 
public class BeanCiudadProvincia implements Serializable{  

	private static final long serialVersionUID = 1L;
	
	public List<String> autocompletadoProvincia(String query){   
		List<String> lista = new ArrayList<String>();
		lista.add("Andalucia");lista.add("Madrid");
		lista.add("Asturias");lista.add("Castilla y leon");
		lista.add("Cataluña");lista.add("Valenciana");
		
		List<String> provincias = new ArrayList<String>();
		for (String provincia: lista)
			if (provincia.toLowerCase().startsWith(query.toLowerCase()))
				provincias.add(provincia);
		
		return provincias;
	}
	
	public List<String> autocompletadoCiudad(String query){
		List<String> lista = new ArrayList<String>();
		lista.add("Sevilla");lista.add("Madrid");
		lista.add("Oviedo");lista.add("Leon");
		lista.add("Barcelona");lista.add("Valencia"); 
		
		List<String> ciudades = new ArrayList<String>();
		for (String ciudad: lista)
			if (ciudad.toLowerCase().startsWith(query.toLowerCase()))
				ciudades.add(ciudad);
		
		return ciudades;
	}

}
