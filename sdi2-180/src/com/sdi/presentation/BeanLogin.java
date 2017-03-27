package com.sdi.presentation; 

import java.io.Serializable;  
import java.util.Map;

import javax.faces.bean.ManagedBean;  
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import alb.util.log.Log;

import com.sdi.business.PublicoService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
    
@ManagedBean(name = "login")                        
@SessionScoped    
public class BeanLogin implements Serializable{
	private static final long serialVersionUID = 6L;
	private String name = "";
	private String password = "";
	private String result = "login_form_result_valid";

	public BeanLogin() {
//		System.out.println("BeanLogin - No existia");
	}

	public String verify() {
		PublicoService ps = Factories.services.createPublicoService();
		User user = ps.iniciarSesion(name, password);
		if (user != null) {
			putUserInSession(user);
			Log.info("El usuario [%s] ha iniciado la sesion",user.getLogin());
			return "exito";
		}
		setResult("login_form_result_error");
		Log.info("El usuario no ha podido iniciar la sesion");
		return "error";
	}

	private void putUserInSession(User user) {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		session.put("LOGGEDIN_USER", user);
	}
	
	public void cerrarSesion(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		
		User u=(User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		
		req.getSession().invalidate();
		
		Log.info("El usuario [%s] ha cerrado sesion", u.getLogin());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
