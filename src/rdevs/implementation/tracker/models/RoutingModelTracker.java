package rdevs.implementation.tracker.models;

import java.util.Optional;
import rdevs.implementation.tracker.entities.Event;
import rdevs.implementation.tracker.entities.InputPort;
import rdevs.implementation.tracker.entities.OutputPort;
import rdevs.implementation.tracker.enums.TypeEvent;

public class RoutingModelTracker {
	
	int id;
	String name;
	EssentialModelInstanceTracker EModel;
	InputPort entrance;
	OutputPort exit;
	NetworkModelTracker NModel;
	double clock;
	
	public RoutingModelTracker(String name, int id, EssentialModelInstanceTracker em) {
		this.name = name;
		this.id = id;
		EModel = em;
		this.EModel.addRoutingModelReference(this);
		entrance = new InputPort("InputPort of "+this.getNameAndID());
		exit = new OutputPort("OutputPort of "+this.getNameAndID());
		exit.setRm(this); 
		entrance.setRm(this);
		this.clock = 0;
	}

	public void addNetworkModelReference(NetworkModelTracker networkModelTracker) {
		this.NModel = networkModelTracker;
	}
	

	public void showData() {
		System.out.println("ROUTING MODEL TRACKER");
		System.out.println("Name: " + this.name);
		System.out.println("ID: " + this.id);
		System.out.println("Embeds EM: " + this.EModel.getName());
		this.showOutputEvents();
	}

	/*
	 * Este método debería mostrar renglón a renglón los eventos que han salido desde este
	 * routing tracker, con toda su información.
	 */
	private void showOutputEvents() {
		System.out.println("Output Events:");
		for(Event event : this.exit.getEvents()) {
			event.showEvent(); 
		}
	}
	
	public EssentialModelInstanceTracker getEssentialModelInstance() {
		return this.EModel;
	}


	public String getNameAndID() {
		return "(" + this.name + "," + this.id + ")";
	}

	public InputPort getEntrance() {
		return entrance;
	}

	public void setEntrance(InputPort entrance) {
		this.entrance = entrance;
	}

	public OutputPort getExit() {
		return exit;
	}

	public void setExit(OutputPort exit) {
		this.exit = exit;
	}
	
	/*
	 * Este método debería tomar los datos como argumento y generar el evento que se manda desde
	 * este modelo hacia todos los demas (con todo lo que implica).
	 */
	public void sendingOutputEvent(int sourceID, String highLevelEventType, int[] destinationsID) {
		
		Optional<RoutingModelTracker> source = this.NModel.getEntities().stream().filter(e -> (e.getId() == sourceID)).findFirst();
		Optional<RoutingModelTracker> destination;
		for(int d : destinationsID) {			
			if(d == -2) {
				new Event(this.clock,TypeEvent.valueOf("External"), source.get().getExit(), this.NModel.EOP,highLevelEventType);	
			}else {
				destination = this.NModel.getEntities().stream().filter(e -> (e.getId() == d)).findFirst();
				new Event(this.clock,TypeEvent.valueOf("Internal"), source.get().getExit(), destination.get().getEntrance(),highLevelEventType);
			}
		}
	}
	
	/*
	 * Este método debería tomar los datos como argumento y generar el evento que se acepta en 
	 * este modelo proveniente de un source específico (con todo lo que implica).
	 */
	public void acceptingInputEvent(int senderID, String highLevelType) {
		if(senderID == 0) {
			Event event = this.NModel.EIP.getEvents().stream()
														.filter(e -> (e.getTarget().equals(this.getEntrance())) && e.getHighLevelType() == highLevelType).findFirst().get();
			event.acceptEvent();
		
		}else {		
			Optional<RoutingModelTracker> RMT = this.NModel.getEntities().stream().filter(e -> e.getId() == senderID).findFirst();
			if(RMT.isPresent()) {
				RMT.get().getExit().getEvents().stream()
												.filter(e -> (e.getTarget().getRm().getId() == this.id) && e.getHighLevelType() == highLevelType)
												.findFirst().get().acceptEvent();
				}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void updateClock(double elapsedTime) {
		this.clock += elapsedTime;
	}
	
}
