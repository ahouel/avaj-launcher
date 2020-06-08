package Flyables;

import Weather.WeatherTower;


/*
 *  Interface of all flyable objects
 */

public interface Flyable {
	
	/*
	 * Update new weather condition using WeatherProvider
	 * Change the object's coordinations depending on it, then if the object landed, unregister it from
	 * the tower.
	 */
	
	public void updateConditions();
	
	/*
	 * Register the tower for this object
	 */
	
	public void registerTower(WeatherTower weatherTower);
}
