package com.sdi.business.impl.classes.registrado;


import java.util.Date;
import java.util.List;

import com.sdi.model.TripStatus;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.exception.PersistenceException;

public class CancelarViaje {

	public void cancelarViaje(List<Trip> trips){
		TripDao tDao = Factories.persistence.createTripDao();
		Transaction transaction = Factories.persistence.createTransaction();
		
		transaction.begin(); 
		try {
			for(Trip t: trips){
				if(t.getStatus()==TripStatus.CANCELLED){
					Log.warn("El viaje[%s] ya esta cancelado previamente", t.getId());
				}else{
					if(t.getClosingDate().after(new Date())){//antes de closingDate, puede cancelar viaje
						t.setStatus(TripStatus.CANCELLED);
						tDao.update(t);
						Log.info("Se ha cancelado correctamente el viaje [%s]", t.getId());
					}else
						Log.info("No se puede cancelar el viaje[%s] dado que haya pasado la fecha de cierre", t.getId());
				}
			}
			transaction.commit(); 
		} catch (PersistenceException e) { 
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion de cancelar viaje");
		 	throw e; 
		}
		
	}
}
