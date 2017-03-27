package com.sdi.business.impl.classes.registrado;

import java.util.ArrayList;
import java.util.List;

import com.sdi.infrastructure.Factories;

import alb.util.log.Log;

import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.exception.PersistenceException;

public class MisViajes {
	
	public List<Trip> misViajes(User u){
		List<Trip> viajes;
		List<Trip> misViajes = new ArrayList<Trip>();
		TripDao tDao = Factories.persistence.createTripDao();
		Transaction transaction = Factories.persistence.createTransaction();
		transaction.begin(); 
		try{
			viajes = tDao.findAll();
			for(Trip t: viajes){//mis viajes en estado no Done
				if(t.getPromoterId().equals(u.getId()) && t.getStatus()!=TripStatus.DONE)
					misViajes.add(t);
			}
			Log.info("Obteniendo viajes del usuario [%s]", u.getLogin());
			transaction.commit();
		}catch(PersistenceException e){
			transaction.rollback();
			Log.warn("Algo ha ocurrido en la transaccion de obtener viajes  del usuario [%s]", u.getLogin());
			throw e; 
		}
		return misViajes;
	}
}
