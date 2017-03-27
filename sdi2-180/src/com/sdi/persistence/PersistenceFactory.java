package com.sdi.persistence;

import com.sdi.persistence.ApplicationDao;
import com.sdi.persistence.RatingDao;
import com.sdi.persistence.SeatDao;
import com.sdi.persistence.TripDao;
import com.sdi.persistence.UserDao;


/**
 * Interfaz de la factoria que suministra implementaciones reales de la fachada 
 * de persistencia para cada Entidad del Modelo (en este caso solo hay 
 * una: Alumno; pero en futuras versiones habr�� m��s)
 *  
 * @author alb
 *
 */
public interface PersistenceFactory {
	
	Transaction createTransaction();
	RatingDao createRatingDao();
	UserDao createUserDao();
	TripDao createTripDao(); 
	SeatDao createSeatDao();
	ApplicationDao createApplicationDao();
	
}

