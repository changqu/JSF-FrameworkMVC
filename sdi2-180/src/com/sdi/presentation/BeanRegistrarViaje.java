package com.sdi.presentation;

import java.io.Serializable; 
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.sdi.infrastructure.Factories;

import alb.util.log.Log;

import com.sdi.model.AddressPoint;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.model.Waypoint;
import com.sdi.model.Trip;   
 
@ManagedBean(name = "registrarViaje")             
@ViewScoped    
public class BeanRegistrarViaje implements Serializable{
	private static final long serialVersionUID = 1L;  
	private Trip trip;
	
	private String salidaAddress;
	private String salidaCity;
	private String salidaState;
	private String salidaCountry;
	private String salidaZipCode;
	private Double salidaLat;
	private Double salidaLon;
	
	private String llegadaAddress;
	private String llegadaCity;
	private String llegadaState;
	private String llegadaCountry;
	private String llegadaZipCode;
	private Double llegadaLat;
	private Double llegadaLon;
	
	private Date arrivalDate;
	private Date departureDate;
	private Date closingDate;
	private Integer availablePax = 0; 
	private Integer maxPax = 0;
	private Double estimatedCost = 0.0;
	private String comments; 
	
	private String mensaje="registrarViajeValido";
	
	public String getSalidaAddress() {
		return salidaAddress;
	}

	public void setSalidaAddress(String salidaAddress) {
		this.salidaAddress = salidaAddress;
	}

	public String getSalidaCity() {
		return salidaCity;
	}

	public void setSalidaCity(String salidaCity) {
		this.salidaCity = salidaCity;
	}

	public String getSalidaState() {
		return salidaState;
	}

	public void setSalidaState(String salidaState) {
		this.salidaState = salidaState;
	}

	public String getSalidaCountry() {
		return salidaCountry;
	}

	public void setSalidaCountry(String salidaCountry) {
		this.salidaCountry = salidaCountry;
	}

	public String getSalidaZipCode() {
		return salidaZipCode;
	}

	public void setSalidaZipCode(String salidaZipCode) {
		this.salidaZipCode = salidaZipCode;
	}

	public Double getSalidaLat() {
		return salidaLat;
	}

	public void setSalidaLat(Double salidaLat) {
		this.salidaLat = salidaLat;
	}

	public Double getSalidaLon() {
		return salidaLon;
	}

	public void setSalidaLon(Double salidaLon) {
		this.salidaLon = salidaLon;
	}

	public String getLlegadaAddress() {
		return llegadaAddress;
	}

	public void setLlegadaAddress(String llegadaAddress) {
		this.llegadaAddress = llegadaAddress;
	}

	public String getLlegadaCity() {
		return llegadaCity;
	}

	public void setLlegadaCity(String llegadaCity) {
		this.llegadaCity = llegadaCity;
	}

	public String getLlegadaState() {
		return llegadaState;
	}

	public void setLlegadaState(String llegadaState) {
		this.llegadaState = llegadaState;
	}

	public String getLlegadaCountry() {
		return llegadaCountry;
	}

	public void setLlegadaCountry(String llegadaCountry) {
		this.llegadaCountry = llegadaCountry;
	}

	public String getLlegadaZipCode() {
		return llegadaZipCode;
	}

	public void setLlegadaZipCode(String llegadaZipCode) {
		this.llegadaZipCode = llegadaZipCode;
	}

	public Double getLlegadaLat() {
		return llegadaLat;
	}

	public void setLlegadaLat(Double llegadaLat) {
		this.llegadaLat = llegadaLat;
	}

	public Double getLlegadaLon() {
		return llegadaLon;
	}

	public void setLlegadaLon(Double llegadaLon) {
		this.llegadaLon = llegadaLon;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) { 
		this.estimatedCost = estimatedCost;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	private boolean tripDefecto; 
	

	public Trip getTrip() {
		return trip; 
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	public boolean isTripDefecto() {
		return tripDefecto;
	}

	public void setTripDefecto(boolean tripDefecto) {
		this.tripDefecto = tripDefecto;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void registrarViaje() {   
		if(availablePax>=maxPax){
			setMensaje("errorRelacionPlaza");
			Log.warn("Se ha producido un error de relacion de plaza al intentar registrarViaje");
		}  
		else if(closingDate.after(departureDate) || departureDate.after(arrivalDate) 
				|| closingDate.before(new Date())){
			setMensaje("errorRelacionFecha");
			Log.warn("Se ha producido un error de relacion de fecha al intentar registrarViaje");
		}
		else{
			AddressPoint salida = new AddressPoint(salidaAddress, salidaCity,salidaState,
			 salidaCountry,salidaZipCode, new Waypoint(salidaLat, salidaLon));
			AddressPoint llegada = new AddressPoint(llegadaAddress, llegadaCity,llegadaState,
					llegadaCountry,llegadaZipCode, new Waypoint(llegadaLat, llegadaLon));
			trip=new Trip();
			trip.setArrivalDate(arrivalDate);
			trip.setAvailablePax(availablePax);
			trip.setClosingDate(closingDate);
			trip.setComments(comments);
			trip.setDeparture(salida);
			trip.setDepartureDate(departureDate);
			trip.setDestination(llegada);
			trip.setEstimatedCost(estimatedCost);
			trip.setMaxPax(maxPax);
			User u=(User) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("LOGGEDIN_USER");
			trip.setPromoterId(u.getId());
			trip.setStatus(TripStatus.OPEN);
			try{
				Factories.services.createRegistradoService().registrarViaje(trip);
				setMensaje("viajeRegistradoConExito");
				Log.info("El usuario [%s] ha creado un nuevo viaje", u.getLogin());
			}catch(Exception e){
				setMensaje("viajeRegistradoRepetido");
				Log.error("Error al intentar registrar viaje, viaje ya existe");
			}
		} 
	}	
	
	//el inputText hace la llamada al metodo set del atributo al tener una accion de update, 
	//lo de #{bean.propiedad} accede al get del atributo. el metodo aqui hace el set
	//entonce al detecetar cambio el get muestra resultado
	@SuppressWarnings("deprecation")
	public void cargarViajeDefecto() {
		if (!tripDefecto){
			setSalidaAddress(null);setSalidaCity(null);
			setSalidaState(null);setSalidaCountry(null);
			setSalidaZipCode(null);setSalidaLat(null);
			setSalidaLon(null);
			
			setLlegadaAddress(null);setLlegadaCity(null);
			setLlegadaState(null);setLlegadaCountry(null);
			setLlegadaZipCode(null);setLlegadaLat(null);
			setLlegadaLon(null);
			
			setArrivalDate(null);setClosingDate(null);
			setDepartureDate(null);
			
			setMaxPax(0);setAvailablePax(0);
			setEstimatedCost(0.0D);setComments(null);
		}else{
			setSalidaAddress("Calle salida");setSalidaCity("Oviedo");
			setSalidaState("Asturias");setSalidaCountry("España");
			setSalidaZipCode("666666");setSalidaLat(null);
			setSalidaLon(null);
			
			setLlegadaAddress("Calle llegada");setLlegadaCity("Madrid");
			setLlegadaState("Madrid");setLlegadaCountry("España");
			setLlegadaZipCode("666666");setLlegadaLat(null);
			setLlegadaLon(null);
		
			setClosingDate(new Date(2017,06,6));setDepartureDate(new Date(2017,06,7));
			setArrivalDate(new Date(2017,06,8));
			
			setMaxPax(5);setAvailablePax(4);
			setEstimatedCost(100D);setComments("Un rollo");
			Log.info("Cargando valores por defecto para registrar viaje");
		}
	}

}
