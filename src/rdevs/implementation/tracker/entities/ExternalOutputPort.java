package rdevs.implementation.tracker.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import rdevs.implementation.tracker.models.NetworkModelTracker;

public class ExternalOutputPort {
	NetworkModelTracker NModel;//Los External couplings que salen de este port
	@Expose String name;			   // se guardan en el modelo de red. Se puede cambiar de ser necesario.
	/*@Expose*/ List<Event> events;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ExternalOutputPort(String name, NetworkModelTracker m) {
		this.name = name;
		this.NModel= m;
		events = new ArrayList<>();
	}
	
	public void addEvent(Event e) {
		this.events.add(e);
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
