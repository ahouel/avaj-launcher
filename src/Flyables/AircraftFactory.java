package Flyables;


/*
 * Factory creating aircrafts
 * 
 */

public class AircraftFactory {
	
	/*
	 * Create a new aircraft depending on the type given.
	 */
	
	public Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
	{
		Coordinates coordinates = new Coordinates(longitude, latitude, height);

		switch (type)
		{
		case "Helicopter":
			return new Helicopter(name, coordinates);
		case "Baloon":
			return new Baloon(name, coordinates);
		case "JetPlane":
			return new JetPlane(name, coordinates);
		default:
			return null;
		}
	}
}