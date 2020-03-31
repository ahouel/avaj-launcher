package Weather;

import java.util.ArrayList;
import java.util.List;

import Flyables.Flyable;

public class Tower {
	private List<Flyable>	observers = new ArrayList<Flyable>();
	
	public void register(Flyable flyable)
	{
		this.observers.add(flyable);
	}
	
	public void unregister(Flyable flyable)
	{
		this.observers.remove(flyable);
	}
	
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
