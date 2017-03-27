package com.sdi.business.impl;

import java.util.List;

import com.sdi.business.PublicoService;
import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.business.impl.classes.publico.IniciarSesion;
import com.sdi.business.impl.classes.publico.RegistrarUsuario;
import com.sdi.business.impl.classes.publico.ViajesActivosPublico;
import com.sdi.model.Trip;
import com.sdi.model.User;

public class SimplePublicoService implements PublicoService{

	@Override
	public User iniciarSesion(String identificador, String contrasena) {
		return new IniciarSesion().validar(identificador, contrasena);
	}

	@Override
	public void registrarUsuario(User nuevoUsuario) throws EntityAlreadyExistsException {
		new RegistrarUsuario().registrar(nuevoUsuario);
	}

	@Override
	public List<Trip> viajesActivosPublico() {
		return new ViajesActivosPublico().obtenerViajes();
	}

}
