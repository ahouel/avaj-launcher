package Weather;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import Flyables.AircraftFactory;
import Flyables.Baloon;
import Flyables.Flyable;
import Flyables.Helicopter;
import Flyables.JetPlane;

/*
 * Class managing the simulation, including the names of aircrafts, the number of loop changing the weather,
 * the types of weather, and the modifiers applying on the coordinates of the aircrafts depending on weather.
 * Contains an aircraftFactory which build aircrafts, and the weatherTower used to change weather.
 */

public class Simulation {
	public static final String									baloonKey = Baloon.class.getSimpleName();
	public static final String									jetPlaneKey = JetPlane.class.getSimpleName();
	public static final String									helicopterKey = Helicopter.class.getSimpleName();
	public static final List<String> 							airCrafts = Arrays.asList(
			"Helicopter",
			"JetPlane",
			"Baloon"
		);
	public static final String[] 								weather = {
			"RAIN",
			"FOG",
			"SUN",
			"SNOW"
	};
	private static Hashtable<String, Hashtable<String, int[]>>	cooModifier;
	private int													simulationsNb;
	private WeatherTower										weatherTower;
	private AircraftFactory										aircraftFactory;
	
	/*
	 * Constructor
	 */
	
	public Simulation(int simulationsNb)
	{
		this.initializeCoo();
		this.simulationsNb = simulationsNb;
		this.weatherTower = new WeatherTower();
		this.aircraftFactory = new AircraftFactory();
	}

	
	/*
	 * Initialize coordinates modifier for aircrafts depending on the weather
	 */
	
	private void initializeCoo()
	{
		Hashtable<String, int[]> baloonCoo = new Hashtable<String, int[]>();
		Hashtable<String, int[]> helicopterCoo = new Hashtable<String, int[]>();
		Hashtable<String, int[]> jetPlaneCoo = new Hashtable<String, int[]>();
		
		for (String weather : Simulation.weather)
		{
			switch(weather)
			{
			case "RAIN":
				baloonCoo.put(weather, new int[]{0, 0, -5});
				helicopterCoo.put(weather, new int[]{5, 0, 0});
				jetPlaneCoo.put(weather, new int[]{0, 5, 0});
				break;
			case "FOG":
				baloonCoo.put(weather, new int[]{0, 0, -3});
				helicopterCoo.put(weather, new int[]{1, 0, 0});
				jetPlaneCoo.put(weather, new int[]{0, 1, 0});
				break;
			case "SUN":
				baloonCoo.put(weather, new int[]{2, 0, 4});
				helicopterCoo.put(weather, new int[]{10, 0, 2});
				jetPlaneCoo.put(weather, new int[]{10, 0, 2});
				break;
			case "SNOW":
				baloonCoo.put(weather, new int[]{0, 0, -15});
				helicopterCoo.put(weather, new int[]{0, 0, -12});
				jetPlaneCoo.put(weather, new int[]{0, 0, -7});
				break;
			}
		}
		Simulation.cooModifier = new Hashtable<String, Hashtable<String, int[]>>();
		Simulation.cooModifier.put(Simulation.baloonKey, baloonCoo);
		Simulation.cooModifier.put(Simulation.jetPlaneKey, jetPlaneCoo);
		Simulation.cooModifier.put(Simulation.helicopterKey, helicopterCoo);
	}
	
	
	/*
	 * Register a newly created flyable
	 */
	
	public void addFlyable(Flyable flyable)
	{
		this.weatherTower.register(flyable);
		flyable.registerTower(this.weatherTower);
	}
	
	
	/*
	 * Starting the simulation
	 */
	
	public void start()
	{
		while (this.simulationsNb-- > 0)
		{
			this.weatherTower.changeWeather();
		}
	}
	
	
	/*
	 * Getters
	 */
	
	public static int[] getCooModifier(String flyableKey, String weather)
	{
		return Simulation.cooModifier.get(flyableKey).get(weather);
	}
	
	public AircraftFactory getFactory()
	{
		return this.aircraftFactory;
	}
}
