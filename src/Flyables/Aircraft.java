package Flyables;

/*
 * Super class for all aircrafts.
 * Contains it's name, coordinates, and an unique id.
 */

public class Aircraft {
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;
	private static long		idCounter = 0;
	
	
	/*
	 * Constructor
	 */
	
	protected Aircraft(String name, Coordinates coordinates)
	{
		this.name = name;
		this.coordinates = coordinates;
		this.id = nextId();
	}
	
	
	/*
	 * Get the next free id and increment the counter
	 */
	
	private long nextId()
	{
		Aircraft.idCounter = Aircraft.idCounter + 1;
		return Aircraft.idCounter;
	}
}
