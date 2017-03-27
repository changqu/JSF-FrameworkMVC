package com.sdi.business.impl.classes.registrado;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Application;
import com.sdi.model.Seat;

import alb.util.log.Log;

import com.sdi.model.Trip;
import com.sdi.model.User;
import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.exception.PersistenceException;

public class SolicitarPlaza {
	
	public void solicitarPlaza(User u, Trip t){
		Long viajeId = t.getId();
		Long userId = u.getId();
		Long[] l = {userId, viajeId};
		ApplicationDao aDao = Factories.persistence.createApplicationDao();
		SeatDao sDao = Factories.persistence.createSeatDao();
		Transaction transaction = Factories.persistence.createTransaction();
		transaction.begin(); 
		try {
			Application a = aDao.findById(l);
			Seat s = sDao.findById(l);
			Application application = new Application();
			if(a==null && s==null){
				application.setTripId(viajeId);
				application.setUserId(userId);
				aDao.save(application);
				Log.info("Insertando solicitud del user [%s] al tApplication de la viaje [%s]", userId, viajeId);
				//no existe el caso de no poder solicitarplaza dado que las viajes sin plazas no se muestran al usuario
			}else{
				Log.warn("El user [%s] ya tiene solicitud con el viaje o ya es miembro o esta excluido al viaje [%s]", userId, viajeId);
			}
			transaction.commit(); 
		} catch (PersistenceException e) { 
			transaction.rollback();
			Log.error("Ha ocurrido algo en transaccion de solicitar plaza");
		 	throw e; 
		}
		
	}
}
