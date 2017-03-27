package com.sdi.persistence;

import java.util.Date;
import java.util.List;

import com.sdi.model.Trip;
import com.sdi.persistence.util.GenericDao;

public interface TripDao extends GenericDao<Trip, Long> {

	Trip findByPromoterIdAndArrivalDate(Long id, Date arrivalDate);
	List<Trip> findOpen();
	List<Trip> findNoDoneByPromoter(Long id);//este consulta hace que la lista de viajes aparece sin orden
											//y hace fallar las pruebas.				
}
