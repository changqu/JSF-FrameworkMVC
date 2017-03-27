package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.RegistradoService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.impl.classes.registrado.CancelarSolicitud;
import com.sdi.business.impl.classes.registrado.CancelarViaje;
import com.sdi.business.impl.classes.registrado.ConfirmarPasajero;
import com.sdi.business.impl.classes.registrado.ExcluirPasajero;
import com.sdi.business.impl.classes.registrado.MisViajes;
import com.sdi.business.impl.classes.registrado.ModificarViaje;
import com.sdi.business.impl.classes.registrado.RegistrarViaje;
import com.sdi.business.impl.classes.registrado.SolicitarPlaza;
import com.sdi.business.impl.classes.registrado.ViajesActivosRegistrado;
import com.sdi.business.impl.classes.registrado.ViajesRelacionados;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class SimpleRegistradoService implements RegistradoService{

	@Override
	public User obtenerPromotor(Trip t) {
		return new ViajesActivosRegistrado().obtenerPromotor(t);
	}

	@Override
	public List<User> obtenerParticipantes(Trip t) {
		return new ViajesActivosRegistrado().obtenerParticipantes(t);
	}
	
	@Override
	public List<User> obtenerParticipantesExcluidos(Trip t) {
		return new ViajesActivosRegistrado().obtenerParticipantesExcluidos(t);
	}
	
	@Override
	public List<User> obtenerSolicitantes(Trip t) {
		return new ViajesActivosRegistrado().obtenerSolicitantes(t);
	}

	@Override
	public String obtenerRelacion(User u, Trip t) {
		return new ViajesActivosRegistrado().obtenerRelacion(u, t);
	}

	@Override
	public void solicitarPlaza(User u, Trip t) {
		new SolicitarPlaza().solicitarPlaza(u, t);
	}

	@Override
	public void cancelarSolicitud(User u, Trip t) {
		new CancelarSolicitud().cancelarSolicitud(u, t);
	}

	@Override
	public List<Trip> misViajes(User u) {
		return new MisViajes().misViajes(u);
	}

	@Override
	public List<Trip> viajesRelacionados(User u) {
		return new ViajesRelacionados().viajesRelacionados(u);
	}

	@Override
	public boolean confirmarPasajero(Trip t, User u) {
		return new ConfirmarPasajero().confirmarPasajero(t,u);
	}

	@Override
	public void excluirPasajero(Trip t, User u) {
		new ExcluirPasajero().excluirPasajero(t, u);
	}

	@Override
	public void registrarViaje(Trip t) throws EntityAlreadyExistsException{
		new RegistrarViaje().registrarViaje(t);
	}

	@Override
	public void modificarViaje(Trip t) throws EntityAlreadyExistsException{
		new ModificarViaje().modificarViaje(t);
	}

	@Override
	public void cancelarViaje(List<Trip> trips) {
		new CancelarViaje().cancelarViaje(trips);
	}

}
