package Weather;

import java.util.Random;

import Flyables.Coordinates;


/*
 * Provide weather conditions
 */

public class WeatherProvider {
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String[] weather;
	
	
	/*
	 * Constructor
	 */
	
	private WeatherProvider()
	{
		WeatherProvider.weather = Simulation.weather;
	}
	
	
	/*
	 * Getters
	 * The algoritm for a new weather is based on a RNG.
	 */
	
	public static WeatherProvider getProvider()
	{
		return weatherProvider;
	}
	
	public String getCurrentWeather(Coordinates coordinates)
	{
		int	number;
		
		number = coordinates.getHeight() + coordinates.getLongitude() + coordinates.getLatitude();
		number += new Random().nextInt(3);
		return WeatherProvider.weather[number % 4];
	}
}
