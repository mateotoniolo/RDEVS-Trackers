package rdevs.implementation.tracker.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import rdevs.implementation.tracker.models.RoutingModelTracker;

public class InputPort {
	@Expose String name;
	RoutingModelTracker rm;
	@Expose List<Event> events = new ArrayList<>();
	List<Coupling> couplings = new ArrayList<>();//Lista de todos los coupling que entran a este puerto
	ExternalInputCoupling EIC;//Registra el coupling que lo une al ExternalInputPort 
	
	public void setEIC(ExternalInputCoupling eIC) {
		EIC = eIC;
	}

	public InputPort(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoutingModelTracker getRm() {
		return rm;
	}
	public void setRm(RoutingModelTracker rm) {
		this.rm = rm;
	}

	public List<Event> getEvents() {
		return events;
	}
	
	public void addEvent(Event e) {
		this.events.add(e);
	}
	public void addCoupling(Coupling c) {
		this.couplings.add(c);
	}

	public ExternalInputCoupling getEIC() {
		return EIC;
	}
	
}
