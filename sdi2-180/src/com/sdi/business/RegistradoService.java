package com.sdi.business;

import java.util.List;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.model.Trip;
import com.sdi.model.User;

public interface RegistradoService {
	User obtenerPromotor(Trip t);
	List<User> obtenerParticipantes(Trip t);
	List<User> obtenerParticipantesExcluidos(Trip t);
	List<User> obtenerSolicitantes(Trip t);
	String obtenerRelacion(User u, Trip t);
	void solicitarPlaza(User u, Trip t);
	void cancelarSolicitud(User u, Trip t);
	List<Trip> misViajes(User u);
	List<Trip> viajesRelacionados(User u);
	boolean confirmarPasajero(Trip t, User u);
	void excluirPasajero(Trip t, User u);
	void registrarViaje(Trip t) throws EntityAlreadyExistsException;
	void modificarViaje(Trip t) throws EntityAlreadyExistsException;
	void cancelarViaje(List<Trip> trips);

	
}
