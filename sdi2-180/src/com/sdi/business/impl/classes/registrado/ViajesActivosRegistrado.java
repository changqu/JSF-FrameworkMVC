package com.sdi.business.impl.classes.registrado;

import java.util.ArrayList;
import java.util.List;

import alb.util.log.Log;

import com.sdi.model.Application;
import com.sdi.model.SeatStatus;
import com.sdi.infrastructure.Factories;
import com.sdi.model.Seat;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.Transaction;
import com.sdi.persistence.UserDao;
import com.sdi.persistence.exception.PersistenceException;

public class ViajesActivosRegistrado {
	
	private ApplicationDao aDao = Factories.persistence.createApplicationDao();
	private SeatDao sDao = Factories.persistence.createSeatDao();
	private UserDao uDao = Factories.persistence.createUserDao();
	private Transaction transaction = Factories.persistence.createTransaction();
	
	public User obtenerPromotor(Trip t) {
		User promotor = null;
		transaction.begin(); 
		try{
			promotor = uDao.findById(t.getPromoterId());
			Log.info("obteniendo promotor del viaje [%s]", t.getId());
			transaction.commit(); 
		}catch(PersistenceException e){
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion de obtener promotor");
			throw e;
		}
		return promotor;
	}
	
	public List<User> obtenerParticipantes(Trip t) {
		List<User> participantes = new ArrayList<User>();
		transaction.begin(); 
		try{
			for(Seat s: sDao.findByTrip(t.getId()))
				if(s.getStatus()==SeatStatus.ACCEPTED && !s.getUserId().equals(t.getPromoterId()))
					participantes.add(uDao.findById(s.getUserId()));
			Log.info("obteniendo participantes aceptedos del viaje [%s]", t.getId());
			transaction.commit();
		}catch(PersistenceException e){
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion de obtener participantes aceptados");
			throw e;
		}
		return participantes;
	}
	
	public List<User> obtenerParticipantesExcluidos(Trip t) {
		List<User> participantes = new ArrayList<User>();
		transaction.begin(); 
		try{
			for(Seat s: sDao.findByTrip(t.getId()))
				if(s.getStatus()==SeatStatus.EXCLUDED && !s.getUserId().equals(t.getPromoterId()))
					participantes.add(uDao.findById(s.getUserId()));
			Log.info("obteniendo participantes excluidos del viaje [%s]", t.getId());
			transaction.commit();
		}catch(PersistenceException e){
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion de obtener participantes excluidos");
			throw e;
		}
		return participantes;
	}
	
	public List<User> obtenerSolicitantes(Trip t) {
		List<User> solicitantes = new ArrayList<User>();
		transaction.begin();
		try{
			for(Application a : aDao.findByTripId(t.getId()))
				solicitantes.add(uDao.findById(a.getUserId()));
			Log.info("obteniendo solicitantes del viaje [%s]", t.getId());
			transaction.commit();
		}catch(PersistenceException e){
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion de obtener solicitantes de viaje");
			throw e;
		}
		return solicitantes;
	}

	public String obtenerRelacion(User u, Trip t) {
		String relacion = "SIN_RELACION";
		Long[] l = {u.getId(), t.getId()};
		transaction.begin();
		try{
			Seat s = sDao.findById(l);
			Application a = aDao.findById(l);
			if(t.getPromoterId().equals(u.getId())){
				relacion="PROMOTOR";
			}else if(s!=null){//aqui hay que utilizar == para comparar
				if(s.getStatus()==SeatStatus.ACCEPTED)
					relacion="ADMITIDO";
				else
					relacion="EXCLUIDO";
			}else if(a!=null){
				if(t.getAvailablePax()>0 && t.getStatus()!=TripStatus.CLOSED)//viaje OPEN o CANCELLED le ponemos PENDIENTE igual
					relacion="PENDIENTE";
				else
					relacion="SIN_PLAZA";
			}
			Log.info("obteniendo relacion del usuario [%s] con el viaje [%s]", u.getLogin(), t.getId());
			transaction.commit();
		}catch(PersistenceException e){
			transaction.rollback();
			Log.warn("Ha ocurrido algo en transaccion de obtener relacion con el viaje");
			throw e;
		}
		return relacion;
	}
	
	
	
}
