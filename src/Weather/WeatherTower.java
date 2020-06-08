package Weather;

import Flyables.Coordinates;


/*
 * Weather tower from where aircrafts can ask for weather update.
 */

public class WeatherTower extends Tower {
	
	
	/*
	 * Get a new weather depending on coordinates.
	 */
	
	public String getWeather(Coordinates coordinates)
	{
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}
	
	
	/*
	 * Change the current weather for all observers in the super class.
	 */
	
	void changeWeather()
	{
		super.conditionsChanged();
	}
}
