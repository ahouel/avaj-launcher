package Flyables;


/*
 * Coordinates of an object in 3 dimensions
 */

public class Coordinates {
	private int	longitude;
	private int	latitude;
	private int height;
	
	
	/*
	 * Constructor
	 */
	
	protected Coordinates(int longitude, int latitude, int height)
	{
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}
	
	
	/*
	 * Getters
	 */
	
	public int getLongitude()
	{
		return this.longitude;
	}
	
	public int getLatitude()
	{
		return this.latitude;
	}
	
	public int getHeight()
	{
		return this.height;
	}
}
