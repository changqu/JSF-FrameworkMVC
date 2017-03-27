package com.sdi.presentation;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import alb.util.log.Log;

import com.sdi.infrastructure.Factories;
import com.sdi.model.AddressPoint;
import com.sdi.model.Trip;
import com.sdi.model.Waypoint;

@ManagedBean(name = "modificarViaje")         
@SessionScoped        
public class BeanModificarViaje implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
	
	private String mensaje="modificarViajeValido";
	
	private Trip trip;
	
	private Integer originalMaxPax;
	private Integer originalAvailablePax;
	

	public Integer getOriginalMaxPax() {
		return originalMaxPax;
	}

	public void setOriginalMaxPax(Integer originalMaxPax) {
		this.originalMaxPax = originalMaxPax;
	}

	public Integer getOriginalAvailablePax() {
		return originalAvailablePax;
	}

	public void setOriginalAvailablePax(Integer originalAvailablePax) {
		this.originalAvailablePax = originalAvailablePax;
	}

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
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	
	public void modificarViaje() {   
		if(maxPax!=originalMaxPax || availablePax!=originalAvailablePax){
			setMaxPax(originalMaxPax);
			setAvailablePax(originalAvailablePax);
			setMensaje("errorModificacionPlaza");
			Log.warn("Se ha producido un error de cambio de plazas al intentar modificarViaje");
		}
		else if(closingDate.after(departureDate) || departureDate.after(arrivalDate) 
				|| closingDate.before(new Date())){
			setMensaje("errorRelacionFecha");
			Log.warn("Se ha producido un error de relacion de fecha al intentar modificarViaje");
		}
		else{
			AddressPoint salida = new AddressPoint(salidaAddress, salidaCity,salidaState,
			 salidaCountry,salidaZipCode, new Waypoint(salidaLat, salidaLon));
			AddressPoint llegada = new AddressPoint(llegadaAddress, llegadaCity,llegadaState,
					llegadaCountry,llegadaZipCode, new Waypoint(llegadaLat, llegadaLon));
			trip.setArrivalDate(arrivalDate);
//			trip.setAvailablePax(availablePax);
			trip.setClosingDate(closingDate);
			trip.setComments(comments);
			trip.setDeparture(salida);
			trip.setDepartureDate(departureDate);
			trip.setDestination(llegada);
			trip.setEstimatedCost(estimatedCost);
//			trip.setMaxPax(maxPax);
			try{
				Factories.services.createRegistradoService().modificarViaje(trip);
				setMensaje("viajeModificadoConExito");
				Log.info("El usuario [%s] ha modificado viaje", trip.getPromoterId());
			}catch(Exception e){
				setMensaje("viajeRegistradoRepetido");
				Log.error("Error al intentar modificar viaje, viaje ya existe");
			}
		}
	}	
	
	public void cargaViaje(Trip t){ 
		setSalidaAddress(t.getDeparture().getAddress());setSalidaCity(t.getDeparture().getCity());
		setSalidaState(t.getDeparture().getState());setSalidaCountry(t.getDeparture().getCountry());
		setSalidaZipCode(t.getDeparture().getZipCode());setSalidaLat(t.getDeparture().getWaypoint().getLat());
		setSalidaLon(t.getDeparture().getWaypoint().getLon());
		
		setLlegadaAddress(t.getDestination().getAddress());setLlegadaCity(t.getDestination().getCity());
		setLlegadaState(t.getDestination().getState());setLlegadaCountry(t.getDestination().getCountry());
		setLlegadaZipCode(t.getDestination().getZipCode());setLlegadaLat(t.getDestination().getWaypoint().getLat());
		setLlegadaLon(t.getDestination().getWaypoint().getLon());
		
		setClosingDate(t.getClosingDate());setDepartureDate(t.getDepartureDate());
		setArrivalDate(t.getArrivalDate());
		
		setMaxPax(t.getMaxPax());setAvailablePax(t.getAvailablePax());
		setEstimatedCost(t.getEstimatedCost());setComments(t.getComments());
		
		setOriginalMaxPax(t.getMaxPax()); setOriginalAvailablePax(t.getAvailablePax());
		
		setTrip(t);//aqui hay que hace eso ya que trip su id tiene que ser lo mismo que el de antes
	}


}
