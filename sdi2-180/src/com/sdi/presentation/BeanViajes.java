package com.sdi.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.infrastructure.Factories;
import com.sdi.model.Trip;
import com.sdi.model.User;
 
@ManagedBean(name = "viajes")           
@SessionScoped
public class BeanViajes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Trip> trips;
	private List<Trip> tripsFiltrado;
	private Trip trip;
	
	private User promotor;
	private List<User> participantes;
	private List<User> participantesExcluidos;//implementar metodos correspondientes
	private List<User> solicitantes;
	private String relacion;
	
	private List<Trip> misViajes;
	private List<Trip> viajesRelacionados;
	
	private Map<Trip, Boolean> viajesCancelados = new HashMap<Trip, Boolean>();
	
	private String mensajeConfirmarPasajero = "confirmarPasajeroVacio";
	
	
	public String viajesActivos() { 
		trips=Factories.services.createPublicoService().viajesActivosPublico();
		return "exito";
	}
	
	public void viajeDetalle(Trip t){
		setTrip(t);
		User u=(User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		
		promotor=Factories.services.createRegistradoService().obtenerPromotor(t);
		participantes=Factories.services.createRegistradoService().obtenerParticipantes(t);
		participantesExcluidos=Factories.services.createRegistradoService().obtenerParticipantesExcluidos(t);
		solicitantes=Factories.services.createRegistradoService().obtenerSolicitantes(t);
		relacion=Factories.services.createRegistradoService().obtenerRelacion(u, t);
	}
	
	public void inicializarViajes(){
		obtenerMisViajes();
		obtenerViajesRelacionados();
		//inicializar mapa  
		for(Trip t: misViajes)
			viajesCancelados.put(t, false);
	}
	
	public void solicitarPlaza(){
		User u=(User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		Factories.services.createRegistradoService().solicitarPlaza(u, trip);
		inicializarViajes();
	}
	
	public void obtenerMisViajes(){
		User u=(User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		misViajes=Factories.services.createRegistradoService().misViajes(u);
	}
	
	public void obtenerViajesRelacionados(){
		User u=(User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		viajesRelacionados=Factories.services.createRegistradoService().viajesRelacionados(u);
	}
	
	public void inicializarInvolucrados(){
		participantes=Factories.services.createRegistradoService().obtenerParticipantes(trip);
		participantesExcluidos=Factories.services.createRegistradoService().obtenerParticipantesExcluidos(trip);
		solicitantes=Factories.services.createRegistradoService().obtenerSolicitantes(trip);
	}
	
	public void confirmarPasajero(User userSeleccionado){ //esto no cambia mi viaje ni viajesRelacionados
		if(Factories.services.createRegistradoService().confirmarPasajero(trip, userSeleccionado)){
			setMensajeConfirmarPasajero("confirmarPasajeroCorrecto");
			inicializarInvolucrados();
		}else{
			setMensajeConfirmarPasajero("confirmarPasajeroError");
		}
	} 

	public void excluirPasajero(User userSeleccionado){ //esto no cambia mi viaje ni viajesRelacionados
		Factories.services.createRegistradoService().excluirPasajero(trip, userSeleccionado);
		setMensajeConfirmarPasajero("mensajeExcluirPasajero");
		inicializarInvolucrados();    
	}
	
	public void cancelarSolicitud(){
		User u=(User) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("LOGGEDIN_USER");
		Factories.services.createRegistradoService().cancelarSolicitud(u, trip);
		inicializarViajes();
	}
	 
	public void cancelarViajes(){
		List<Trip> trips = new ArrayList<Trip>();
		for(Trip t: viajesCancelados.keySet())
			if(viajesCancelados.get(t))//si es true, el viaje es elegido
				trips.add(t);
		Factories.services.createRegistradoService().cancelarViaje(trips);
	}
	
	
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}
	public List<Trip> getTripsFiltrado() {
		return tripsFiltrado;
	}
	public void setTripsFiltrado(List<Trip> tripsFiltrado) {
		this.tripsFiltrado = tripsFiltrado;
	}

	public User getPromotor() {
		return promotor;
	}

	public void setPromotor(User promotor) {
		this.promotor = promotor;
	}

	public List<User> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<User> participantes) {
		this.participantes = participantes;
	}

	public String getRelacion() {
		return relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public List<User> getSolicitantes() {
		return solicitantes;
	}

	public void setSolicitantes(List<User> solicitantes) {
		this.solicitantes = solicitantes;
	}

	public List<Trip> getMisViajes() {
		return misViajes;
	}

	public void setMisViajes(List<Trip> misViajes) {
		this.misViajes = misViajes;
	}

	public List<Trip> getViajesRelacionados() {
		return viajesRelacionados;
	}

	public void setViajesRelacionados(List<Trip> viajesRelacionados) {
		this.viajesRelacionados = viajesRelacionados;
	}
	
	public List<User> getParticipantesExcluidos() {
		return participantesExcluidos;
	}

	public void setParticipantesExcluidos(List<User> participantesExcluidos) {
		this.participantesExcluidos = participantesExcluidos;
	}

	public Map<Trip, Boolean> getViajesCancelados() {
		return viajesCancelados;
	}

	public void setViajesCancelados(Map<Trip, Boolean> viajesCancelados) {
		this.viajesCancelados = viajesCancelados;
	}

	public String getMensajeConfirmarPasajero() {
		return mensajeConfirmarPasajero;
	}

	public void setMensajeConfirmarPasajero(String mensajeConfirmarPasajero) {
		this.mensajeConfirmarPasajero = mensajeConfirmarPasajero;
	}
	
}
