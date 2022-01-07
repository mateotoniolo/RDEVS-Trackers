package rdevs.implementation.tracker.entities;

import com.google.gson.annotations.Expose;

public class ConcreteEvent {
	@Expose Event event;
	
	
	public ConcreteEvent(Event e) {
		this.event = e;
	}
}
