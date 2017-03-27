package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import alb.util.log.Log;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.model.UserStatus;
  
@ManagedBean(name = "registrarse")          
@ViewScoped  	//el bean creado su alcance solo se mantiena pagina actual que estas, si recarga la pagina la misma pagina, el bean se crea de nuevo
public class BeanRegistrarse implements Serializable{ 
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellidos;
	private String identificador;
	private String email;
	private String password;
	private String repetirPassword;
	
	private String result="registrarse_form_result_valid";

	public BeanRegistrarse() { 
	}

	public void registrarse() {
		User nuevoUsuario = new User(); 
		if (!password.equals(repetirPassword)) {
			setResult("passwordNoCoincide");
			Log.error("Las contraseñas no coinciden");
		}
		else{
			nuevoUsuario.setName(nombre);
			nuevoUsuario.setSurname(apellidos); 
			nuevoUsuario.setEmail(email);
			nuevoUsuario.setLogin(identificador);
			nuevoUsuario.setPassword(password);
			nuevoUsuario.setStatus(UserStatus.ACTIVE);
			try { 
				Factories.services.createPublicoService().registrarUsuario(nuevoUsuario);
				setResult("registradoCorrecto");
			} catch (EntityAlreadyExistsException e) {
				setResult("loginYaExiste");
				Log.error("El identificador de usuario ya existe");
			}
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password; 
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepetirPassword() {
		return repetirPassword;
	}

	public void setRepetirPassword(String repetirPassword) {
		this.repetirPassword = repetirPassword;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
