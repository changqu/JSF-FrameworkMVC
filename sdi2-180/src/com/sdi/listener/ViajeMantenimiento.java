package com.sdi.listener;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.exception.PersistenceException;

public class ViajeMantenimiento implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {			
			@Override
			public void run() {
				updateTrips();
			}
		}, 5000, 5000);
	}
	
	private void updateTrips() {
		TripDao tDao = Factories.persistence.createTripDao();
		Transaction transaction = Factories.persistence.createTransaction();
		
		List<Trip> viajes = tDao.findAll();
		for (Trip t: viajes) {
			if (cambiarEstado(t)){
				transaction.begin(); 
				try {
					tDao.update(t);
					transaction.commit(); 
				} catch (PersistenceException e) { 
					transaction.rollback();
					Log.info("Ha ocurrido algo en transaccion de mantenimiento de viajes");
				 	throw e; 
				}
			}
		}
	}
	
	private boolean cambiarEstado(Trip t) {
		if (t.getClosingDate().before(new Date()) && t.getArrivalDate().after(new Date()) &&
				t.getStatus() != TripStatus.CLOSED) {
			t.setStatus(TripStatus.CLOSED);
			Log.info("El viaje [%s] ha cambiado su estado al CLOSED", t.getId());
			return true;
		}
		if (t.getArrivalDate().before(new Date()) && t.getStatus() != TripStatus.DONE) {
			t.setStatus(TripStatus.DONE);
			Log.info("El viaje [%s] ha cambiado su estado al DONE", t.getId());
			return true;
		}
		return false;
	}

}
