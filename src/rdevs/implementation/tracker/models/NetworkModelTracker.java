package rdevs.implementation.tracker.models;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.annotations.Expose;

import rdevs.implementation.tracker.entities.Coupling;
import rdevs.implementation.tracker.entities.Event;
import rdevs.implementation.tracker.entities.ExternalCoupling;
import rdevs.implementation.tracker.entities.ExternalInputCoupling;
import rdevs.implementation.tracker.entities.ExternalInputPort;
import rdevs.implementation.tracker.entities.ExternalOutputCoupling;
import rdevs.implementation.tracker.entities.ExternalOutputPort;
import rdevs.implementation.tracker.entities.InputPort;
import rdevs.implementation.tracker.enums.TypeEvent;

public class NetworkModelTracker {

	@Expose String name;
	@Expose List<RoutingModelTracker> entities;
	@Expose List<Coupling> InternalCoupling;
//	List<ExternalInputCoupling> EIC;
//	List<ExternalOutputCoupling> EOC;
	@Expose ExternalInputPort EIP;
	@Expose ExternalOutputPort EOP;
	@Expose List<ExternalCoupling> Externals; 		//Lista de Todos los ExternalCouplings, tanto input como output
	
	public NetworkModelTracker(String name) {
		this.name = name;
		this.entities = new LinkedList<RoutingModelTracker>();
		this.InternalCoupling = new LinkedList<Coupling>() ;
		this.EIP = new ExternalInputPort("External Input Port of "+this.name,this);
		this.EOP = new ExternalOutputPort("External Output Port of "+this.name,this);
		this.Externals = new LinkedList<ExternalCoupling>();
	}
	
	public void addRoutingModelTracker(RoutingModelTracker rm) {
		if(!entities.isEmpty()) {
			for(RoutingModelTracker roumo : entities) {
				InternalCoupling.add(new Coupling(roumo.exit, rm.entrance));
				InternalCoupling.add(new Coupling(rm.exit,roumo.entrance));
			}	
		}
		entities.add(rm);
		rm.addNetworkModelReference(this);
		Externals.add(new ExternalOutputCoupling(rm.getExit(),EOP));		//Agrega un ExternalOutputCoupling que va del RM al EOP del NM
		Externals.add(new ExternalInputCoupling(rm.getEntrance(),EIP));		//Agrega un ExternalInputCoupling que va del EIP al RM 
	}
	
	public void showData() {
		System.out.println("NETWORK MODEL TRACKER");
		System.out.println("Name: " + this.name);
		List<EssentialModelInstanceTracker> essentials = new LinkedList<EssentialModelInstanceTracker>();
		for(RoutingModelTracker rm: this.entities) {
			System.out.println("-----------------------------------------");
			rm.showData();
			EssentialModelInstanceTracker em = rm.getEssentialModelInstance();
			if (!essentials.contains(em)) essentials.add(em);
		}		
		for(EssentialModelInstanceTracker em: essentials) {
			System.out.println("-----------------------------------------");
			em.showData();
		}
		System.out.println("----------------External Couplings-------------------------");
		for(ExternalCoupling ec: this.Externals) {
			ec.showData();
		}
		System.out.println("----------------Internal Couplings-------------------------");
		for(Coupling ec: this.InternalCoupling) {
			ec.showData();
		}
	}

	public List<RoutingModelTracker> getEntities() {
		return entities;
	}

	public void setEntities(List<RoutingModelTracker> entities) {
		this.entities = entities;
	}
	//accepting input event: crea event asociado al eip de la red, recibe highLevelType, externo
	public void acceptingInputEvent(String highLevelType, int[] destinationsID) {
		for(int id : destinationsID) {
			InputPort IP = this.getEntities().stream().filter(e -> e.getId() == id).findFirst().get().getEntrance();
			new Event(0, TypeEvent.valueOf("External"), IP,this.EIP , highLevelType);
		}
	}
	
	
	// sending output event: crea concrete asociado al eop de la red, high level type, destinations
	public void sendingOutputEvent(String highLevelType, int[] destinationsID) {
		
		for(int id : destinationsID) {
			Event event = this.EOP.getEvents().stream().filter(e -> (e.getTarget().getRm().getId() == id) && e.getHighLevelType().equals(highLevelType)).findFirst().get();
			event.acceptEvent();
		}
		
	}
	
}
