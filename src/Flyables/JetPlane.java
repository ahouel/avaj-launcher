package Flyables;

import Simulator.Writer;
import Weather.Simulation;
import Weather.WeatherProvider;
import Weather.WeatherTower;


/*
 * Jetplane
 */

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower	weatherTower;
	
	
	/*
	 * Constructor
	 */
	
	protected JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	
	/*
	 * (non-Javadoc)
	 * @see Flyables.Flyable#updateConditions()
	 */
	
	@Override
	public void updateConditions() {
		String	weather;
		int[]	cooModifier;
		
		weather = WeatherProvider.getProvider().getCurrentWeather(super.coordinates);
		cooModifier = Simulation.getCooModifier(this.getClass().getSimpleName(), weather);
		Writer.weatherChangeMsg(weather, this.getClass().getSimpleName(), super.name, super.id);
		super.coordinates = new Coordinates(super.coordinates.getLongitude() + cooModifier[0],
				super.coordinates.getLatitude() + cooModifier[1],
				super.coordinates.getHeight() + cooModifier[2]);
		if (super.coordinates.getHeight() < 0)
		{
			Writer.unregisterTowerMsg(this.getClass().getSimpleName(), super.name, super.id);
			weatherTower.unregister(this);
		}
	}

	
	/*
	 * (non-Javadoc)
	 * @see Flyables.Flyable#registerTower(Weather.WeatherTower)
	 */
	
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		Writer.registerTowerMsg(this.getClass().getSimpleName(), super.name, super.id);
	}

}
