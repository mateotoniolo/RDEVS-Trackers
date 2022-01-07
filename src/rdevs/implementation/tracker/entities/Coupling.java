package rdevs.implementation.tracker.entities;

public class Coupling {
	OutputPort origin;
	InputPort  end;
	//Hacer un originID y un endID para no poner informacion redundante
	
	public Coupling(OutputPort op, InputPort ip) {
		this.origin = op;
		this.end = ip;
		origin.addCoupling(this);//Ambos puertos tienen una lista para llevar
		end.addCoupling(this);	// registro de los Couplings pertenecientes 
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
