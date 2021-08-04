package rdevs.implementation.tracker.entities;

public class ExternalInputCoupling extends ExternalCoupling {
	InputPort target;
	ExternalInputPort source;
	
	public ExternalInputCoupling(InputPort t,ExternalInputPort s ) {
		this.source = s;//El EIC conecta un EIP con un InputPort
		this.target = t;//
		target.setEIC(this);//El InputPort guarda el EIC, es uno sólo
	}
	
	public void showData() {
		System.out.println(this.source.getName()+"--->"+ this.target.getName());		
	}
}
