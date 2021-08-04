package rdevs.implementation.tracker.entities;

public class ExternalOutputCoupling extends ExternalCoupling {
	OutputPort source;
	ExternalOutputPort target;
	
	public ExternalOutputCoupling(OutputPort s,ExternalOutputPort t ) {
		this.source = s;//El EOC conecta un OutputPort con un EOP
		this.target = t;
		source.setEOC(this);//El OutputPort guarda el EOC, es uno sólo
	}
	
	public void showData() {
		System.out.println(this.source.getName()+"--->"+ this.target.getName());		
	}
}
