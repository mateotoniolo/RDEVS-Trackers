package rdevs.implementation.tracker.entities;

import com.google.gson.annotations.Expose;

import rdevs.implementation.tracker.enums.TypeEvent;

public class Event {
	@Expose TypeEvent type;
	OutputPort source;
	InputPort target;
	@Expose double timing;
	@Expose String highLevelType;
	@Expose ConcreteEvent concrete;
	ExternalInputPort eip;
	ExternalOutputPort eop;
	// Crear atributo sourceID y targetID que permita identificar el modelo de ruteo que los contiene para poner en el JSON lo mismo para los EIP
		
	public Event(double clock,TypeEvent t, OutputPort s, InputPort tg,String highLevelType) {
		type = t;
		source =s;
		target = tg;
		s.addEvent(this);
		tg.addEvent(this);
		this.timing = clock;
		this.highLevelType = highLevelType;
		this.concrete = null;
			}
	public Event(double clock, TypeEvent t, OutputPort s, ExternalOutputPort tg, String highLevelType) {
		type = t;
		source =s;
		eop = tg;
		s.addEvent(this);
		tg.addEvent(this);
		this.timing = clock;
		this.highLevelType = highLevelType;
		this.concrete = null;
	}
	public Event(double clock, TypeEvent t, InputPort tg, ExternalInputPort s, String highLevelType) {
		type = t;
		eip = s;
		target = tg;
		s.addEvent(this);
		tg.addEvent(this);
		this.timing = clock;
		this.highLevelType = highLevelType;
		this.concrete = null;
	}
	public void acceptEvent() {
		this.concrete = new ConcreteEvent(this);
	}
	
	public TypeEvent getType() {
		return type;
	}
	
	public void setType(TypeEvent type) {
		this.type = type;
	}
	
	public OutputPort getSource() {
		return source;
	}
	
	public void setSource(OutputPort source) {
		this.source = source;
	}
	
	public InputPort getTarget() {
		return target;
	}
	
	public void setTarget(InputPort target) {
		this.target = target;
	}	
	
	public void showEvent() {
		if(this.getType().equals(TypeEvent.Internal)) {
						System.out.println("*INTERNAL EVENT -> Time: " + this.timing + " | From: " + this.source.getRm().getNameAndID() + " --------> To: " + this.target.getRm().getNameAndID()+" | High Level Type: "+ this.highLevelType );
		}
		else {
			//TODO MJB to Mateo: Aca hay que diferenciar los eventos externos entrantes de los externos salientes.
			// MJB to Mateo: Agregar el HighLevelType en el print para ver toda la informaci�n asociada al evento.
			
			System.out.println("*EXTERNAL EVENT -> Time: " + this.timing + " | From: " + this.source.getRm().getNameAndID() + " --------> To: " + this.eop.getName()+" | High Level Type: "+ this.highLevelType );
		}
	}

	public double getTiming() {
		return timing;
	}

	public void setTiming(double timing) {
		this.timing = timing;
	}

	public String getHighLevelType() {
		return highLevelType;
	}

	public void setHighLevelType(String highLevelType) {
		this.highLevelType = highLevelType;
	}
	
}
