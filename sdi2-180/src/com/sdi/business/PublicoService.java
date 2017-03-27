package com.sdi.business;

import java.util.List;

import com.sdi.business.exception.EntityAlreadyExistsException;
import com.sdi.model.User;
import com.sdi.model.Trip;

public interface PublicoService {
	
	User iniciarSesion(String identificador, String contrasena);
	void registrarUsuario(User nuevoUsuario) throws EntityAlreadyExistsException;
	List<Trip> viajesActivosPublico();
	
}
