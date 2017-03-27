package com.sdi.business.impl.classes.registrado;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.TripStatus;

import alb.util.log.Log;

import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.exception.PersistenceException;

//usar transaccion aqui
public class ConfirmarPasajero {

	public boolean confirmarPasajero(Trip t, User u){
		boolean resultado=true;
		
		Long[] l = {u.getId(), t.getId()};
		Seat s = new Seat();
		s.setComment(null);
		s.setStatus(SeatStatus.ACCEPTED);
		s.setTripId(t.getId());
		s.setUserId(u.getId());
		
		ApplicationDao aDao = Factories.persistence.createApplicationDao();
		SeatDao sDao = Factories.persistence.createSeatDao();
		TripDao tDao = Factories.persistence.createTripDao();
		Transaction transaction = Factories.persistence.createTransaction();

		transaction.begin(); 
		try {
			if(t.getAvailablePax()>0){//si plaza libre queda
				aDao.delete(l);
				sDao.save(s);
				t.setAvailablePax(t.getAvailablePax()-1);
				if(t.getAvailablePax()==0){
					t.setStatus(TripStatus.CLOSED);
				}
				tDao.update(t);
				Log.info("Se ha confirmado correctamente el usuario con id [%s] de la viaje [%s]", u.getId(), t.getId());
			}else{
				Log.info("Error, No se puede confirmar usuario con id [%s] de la viaje [%s] dado que no hay plaza libre", u.getId(), t.getId());
				resultado = false;
			}
			transaction.commit(); 
		}catch (PersistenceException e) { 
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion de confirmar pasajero"); 
			resultado = false;
			throw e;
		}
		return resultado;
	}
	
}
