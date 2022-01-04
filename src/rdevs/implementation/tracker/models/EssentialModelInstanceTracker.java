package rdevs.implementation.tracker.models;

import com.google.gson.annotations.Expose;

public class EssentialModelInstanceTracker {

	@Expose String name;
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
