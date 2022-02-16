package Testing;

import rdevs.implementation.tracker.models.EssentialModelInstanceTracker;
import rdevs.implementation.tracker.models.NetworkModelTracker;
import rdevs.implementation.tracker.models.RoutingModelTracker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		/*Prueba*/		
		NetworkModelTracker modeloRed = new NetworkModelTracker("Modelo RED 1");
		
		RoutingModelTracker modeloRuteo1 = new RoutingModelTracker("Modelo RUTEO 1", 1, new EssentialModelInstanceTracker("Modelo ESENCIAL 1"));
		RoutingModelTracker modeloRuteo2 = new RoutingModelTracker("Modelo RUTEO 2", 2, new EssentialModelInstanceTracker("Modelo ESENCIAL 2"));
		RoutingModelTracker modeloRuteo3 = new RoutingModelTracker("Modelo RUTEO 3", 3, new EssentialModelInstanceTracker("Modelo ESENCIAL 3"));
		RoutingModelTracker modeloRuteo4 = new RoutingModelTracker("Modelo RUTEO 4", 4, new EssentialModelInstanceTracker("Modelo ESENCIAL 4"));
		
		modeloRed.addRoutingModelTracker(modeloRuteo1);
		modeloRed.addRoutingModelTracker(modeloRuteo2);
		modeloRed.addRoutingModelTracker(modeloRuteo3);
		modeloRed.addRoutingModelTracker(modeloRuteo4);
		
		int[] arreglo = {2,-2};
		modeloRuteo2.sendingOutputEvent(1, "type1",  arreglo);
		
		int[] array = {2};
		modeloRed.acceptingInputEvent("type2",array );
		
		modeloRuteo2.acceptingInputEvent(0, "type2");
		
		
		//	modeloRed.showData();

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		System.out.println(gson.toJson(modeloRed));
		
			/*Funcion que crea el JSON*/
		 try (PrintStream out = new PrintStream(new FileOutputStream("./Output.json"))) {
	            out.print(gson.toJson(modeloRed));
	        }
	}

}
