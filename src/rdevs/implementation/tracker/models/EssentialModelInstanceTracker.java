package rdevs.implementation.tracker.models;

public class EssentialModelInstanceTracker {

	String name;
	RoutingModelTracker RModel;
	
	public EssentialModelInstanceTracker(String name) {
		this.name = name;
	}
	
	public void addRoutingModelReference(RoutingModelTracker routingModelTracker) {
		this.RModel = routingModelTracker;
	}
	
	public String getName() {
		return this.name;
	}	
	
	public void showData() {
		System.out.println("ESSENTIAL MODEL TRACKER");
		System.out.println("Name: " + this.name);
		System.out.println("Used by: " + this.RModel.getNameAndID());
	}
	
}
