package Weather;

import java.util.ArrayList;
import java.util.List;

import Flyables.Flyable;


/*
 * Tower observing a list of flyable objects.
 */

public class Tower {
	private List<Flyable>	observers = new ArrayList<Flyable>();
	
	
	/*
	 * Register a new flyable.
	 */
	
	public void register(Flyable flyable)
	{
		this.observers.add(flyable);
	}
	
	
	/*
	 * Unregister a flyable from the list.
	 */
	
	public void unregister(Flyable flyable)
	{
		this.observers.remove(flyable);
	}
	
	
	/*
	 * Change the current weather for all observers of the list.
	 * Checks if an observer has landed (unregistered) during the loop.
	 */
	
	protected void conditionsChanged()
	{
		int		size;
		int		i;
		
		i = 0;
		size = observers.size();
		while (i < size)
		{
			this.observers.get(i).updateConditions();
			if (size != observers.size())
				size = observers.size();
			else
				i++;
		}
	}
}
