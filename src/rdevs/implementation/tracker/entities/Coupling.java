package rdevs.implementation.tracker.entities;

import com.google.gson.annotations.Expose;

public class Coupling {
	OutputPort origin;
	InputPort  end;
	@Expose String originFullname;
	@Expose String endFullname;
	@Expose Integer originID;
	@Expose Integer endID;
	//Hacer un originID y un endID para no poner informacion redundante
	
	public Coupling(OutputPort op, InputPort ip) {
		this.origin = op;
		this.end = ip;
		origin.addCoupling(this);//Ambos puertos tienen una lista para llevar
		end.addCoupling(this);	// registro de los Couplings pertenecientes 
		this.originFullname =  "Origin is "+this.origin.name;
		this.endFullname =  "End is "+this.end.name;
		this.originID = this.origin.getRm().getId();
		this.endID = this.end.getRm().getId();
	}

	public OutputPort getOrigin() {
		return origin;
	}

	public void setOrigin(OutputPort origin) {
		this.origin = origin;
	}

	public InputPort getEnd() {
		return end;
	}

	public void setEnd(InputPort end) {
		this.end = end;
	}
	public void showData() {
		System.out.println(this.origin.getName()+"--->"+ this.end.getName());		
	}
	
}
