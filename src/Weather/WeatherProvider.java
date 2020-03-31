package Weather;

import java.util.Random;

import Flyables.Coordinates;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String[] weather;
	
	private WeatherProvider()
	{
		WeatherProvider.weather = Simulation.weather;
	}
	
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
