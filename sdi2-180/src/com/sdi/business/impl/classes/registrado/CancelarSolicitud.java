package com.sdi.business.impl.classes.registrado;

import java.util.Date;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;
import com.sdi.model.SeatStatus;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.exception.PersistenceException;

import alb.util.log.Log;

public class CancelarSolicitud {
	
	public void cancelarSolicitud(User u, Trip t) {//aqui ya se supone que t es un viaje activo
		Long[] l = {u.getId(), t.getId()};
		ApplicationDao aDao = Factories.persistence.createApplicationDao();
		SeatDao sDao = Factories.persistence.createSeatDao();
		TripDao tDao = Factories.persistence.createTripDao();
		Transaction transaction = Factories.persistence.createTransaction();
		
		transaction.begin(); 
		try {
			Application a = aDao.findById(l);
			Seat s = sDao.findById(l);
			if(a!=null){
				aDao.delete(l);
				Log.info("El usuario [%s] se ha cancelado solicitud de plaza para viaje [%s]",u.getLogin(), t.getId());
			}else if(s!=null && t.getClosingDate().after(new Date())){//closingDate es despues de ahora
				sDao.delete(l);
				Log.info("El usuario [%s] se ha cancelado solicitud de plaza para viaje [%s]",u.getLogin(), t.getId());
				if(s.getStatus()==SeatStatus.ACCEPTED){
					t.setAvailablePax(t.getAvailablePax()+1);
					if(t.getStatus()==TripStatus.CLOSED)//si este caso justo lleno y quitas una plaza
						t.setStatus(TripStatus.OPEN);	//el viaje cambia de estado al open
					tDao.update(t);
				}
			}else{
				Log.info("No es posible solicitud del viaje [%s] dado que ha pasado fecha de cierre", t.getId());
			}
			transaction.commit(); 
		}catch (PersistenceException e) { 
			transaction.rollback();
			Log.info("Ha ocurrido algo en transaccion de cancelar solicitud");
			throw e;
		}
		
	}
	
}
