package rdevs.implementation.tracker.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import rdevs.implementation.tracker.models.RoutingModelTracker;

public class OutputPort {
	@Expose String name;
	RoutingModelTracker rm;
	@Expose List<Event> events;
	List<Coupling> couplings;//Lista de todos los coupling que salen de este puerto
	ExternalOutputCoupling EOC;//Registra el coupling que lo une al ExternalOutputPort 
	
	public List<Event> getEvents() {
		return events;
	}
	
	public void addEvent(Event e) {
		this.events.add(e);
	}
	
	public ExternalOutputCoupling getEOP() {
		return EOC;
	}

	public void setEOC(ExternalOutputCoupling EOC) {
		this.EOC = EOC;
	}

	public OutputPort(String name) {
		this.name = name;
		this.events = new ArrayList<>();
		this.couplings = new ArrayList<>();
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
	public void addCoupling(Coupling c) {
		this.couplings.add(c);
	}
}
