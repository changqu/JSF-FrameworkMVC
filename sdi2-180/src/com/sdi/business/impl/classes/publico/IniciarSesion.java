package com.sdi.business.impl.classes.publico;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.exception.PersistenceException;

public class IniciarSesion {

	public User validar(String identificador, String contrasena) {
		UserDao uDao = Factories.persistence.createUserDao();
		Transaction transaction = Factories.persistence.createTransaction();
		transaction.begin(); 
		try {
			User u = uDao.findByLogin(identificador);
			if(u!=null && u.getPassword().equals(contrasena)){
				Log.info("El usuario [%s] se ha validado correctamente", u.getLogin());
				return u;//usuario encontrado
			}
			transaction.commit();
		}catch (PersistenceException e) { 
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion de validar usuario");
		 	throw e; 
		}
		return null;//usuario no encontrado
	}

}
