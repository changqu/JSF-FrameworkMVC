package com.sdi.business.impl.classes.publico;

import java.util.ArrayList;
import java.util.List;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.exception.PersistenceException;

public class ViajesActivosPublico {

	public List<Trip> obtenerViajes(){
		List<Trip> viajesActivo = new ArrayList<Trip>();
		TripDao tDao = Factories.persistence.createTripDao();
		Transaction transaction = Factories.persistence.createTransaction();
		transaction.begin(); 
		try{
			viajesActivo = tDao.findOpen();
			Log.info("obteniendo lista de viajes activos");
			transaction.commit();
		}catch(PersistenceException e){
			transaction.rollback();
			Log.warn("Algo ha ocurrido en la trasaccion de obtener viajes activos");
			throw e;
		}
		return viajesActivo;
	}
	
}
