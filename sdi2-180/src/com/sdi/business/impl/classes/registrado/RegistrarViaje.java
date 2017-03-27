package com.sdi.business.impl.classes.registrado;

import alb.util.log.Log;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.exception.PersistenceException;

public class RegistrarViaje {
	
	public void registrarViaje(Trip t) throws EntityAlreadyExistsException{
		TripDao tDao = Factories.persistence.createTripDao();
		Transaction transaction = Factories.persistence.createTransaction();
		
		transaction.begin(); 
		try {
			tDao.save(t);
			transaction.commit(); 
		} catch (PersistenceException e) { 
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion registrar viaje");
		 	throw e; 
		}
		
	}
}
