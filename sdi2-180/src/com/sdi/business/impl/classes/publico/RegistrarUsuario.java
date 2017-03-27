package com.sdi.business.impl.classes.publico;

import alb.util.log.Log;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.User;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.exception.PersistenceException;


public class RegistrarUsuario {

	public void registrar(User nuevoUsuario) throws EntityAlreadyExistsException{
		UserDao uDao = Factories.persistence.createUserDao();
		Transaction transaction = Factories.persistence.createTransaction();
		transaction.begin(); 
		try {
			if(uDao.findByLogin(nuevoUsuario.getLogin())!=null){
				throw new EntityAlreadyExistsException();
			}else{
				uDao.save(nuevoUsuario);
				Log.info("Nuevo usuario [%s] registrado", nuevoUsuario.getLogin());
			}
			transaction.commit(); 
		} catch (PersistenceException e) { 
			transaction.rollback();
			Log.error("Ha ocurrido algo en transaccion de registrar nuevo usuario");
		 	throw e; 
		}
	}

}
