package Simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import Weather.Simulation;

public class Writer {
	private final boolean										logs = false;
	private final String										fileName = "simulation.txt";
	private static Hashtable<String, Hashtable<String, String>>	aircraftStr;
	private static Writer										writer = new Writer();
	private FileWriter											myWriter;
	
	private Writer()
	{
		try {
			this.myWriter = new FileWriter(this.fileName);
		} catch (IOException e) {
			MyErrors.error("WRITE");
		}
		Writer.initializer();
	}
	
	private static void initializer()
	{
		Hashtable<String, String> baloonStr = new Hashtable<String, String>();
		Hashtable<String, String> jetPlaneStr = new Hashtable<String, String>();
		Hashtable<String, String> helicopterStr = new Hashtable<String, String>();

		for (String weather : Simulation.weather)
		{
			switch (weather)
			{
			case "RAIN":
				baloonStr.put(weather, "Damn you rain ! You mesed up my baloon.");
				jetPlaneStr.put(weather, "It's raining. Better watch out for lightings.");
				helicopterStr.put(weather, "My helix protect me from this rain.");
				break;
			case "FOG":
				baloonStr.put(weather, "Let's get a bit higher to get out of the fog.");
				jetPlaneStr.put(weather, "It's time to trust our softwares, i can't see anything in the fog.");
				helicopterStr.put(weather, "The fog is too dangerous !");
				break;
			case "SUN":
				baloonStr.put(weather, "Let's enjoy the good weather and take some pics.");
				jetPlaneStr.put(weather, "I'm like a pizza in an oven !");
				helicopterStr.put(weather, "This is hot.");
				break;
			case "SNOW":
				baloonStr.put(weather, "It's snowing. We're gonna crash.");
				jetPlaneStr.put(weather, "OMG! Winter is coming!");
				helicopterStr.put(weather, "My rotor is going to freeze!");
				break;
			}
		}
		Writer.aircraftStr = new Hashtable<String, Hashtable<String, String>>();
		Writer.aircraftStr.put(Simulation.baloonKey, baloonStr);
		Writer.aircraftStr.put(Simulation.jetPlaneKey, jetPlaneStr);
		Writer.aircraftStr.put(Simulation.helicopterKey, helicopterStr);
	}
	
	public static Writer getWriter()
	{
		return Writer.writer;
	}
	
	private static String getWeatherStr(String flyableKey, String weather)
	{
		return Writer.aircraftStr.get(flyableKey).get(weather);
	}
	
	private void addLine(String line)
	{
		try {
			this.myWriter.write(line + "\n");
		} catch (IOException e) {
			System.err.println("Error : " + MyErrors.error("WRITE"));
			Writer.writer.closeFile();
		}
	}
	
	public void closeFile()
	{
		try {
			this.myWriter.close();
		} catch (IOException e) {
			System.err.println("Error : " + MyErrors.error("CLOSE"));
			System.exit(-1);
		}
		System.out.println("Successfully wrote " + this.fileName);
	}
	
	private static String flyableStr(String flyable, String name, long id)
	{
		return flyable + "#" + name + "(" + String.valueOf(id) + ")";
	}
	
	public static void registerTowerMsg(String flyable, String name, long id)
	{
		String	str;
		
		str = "Tower says: " + Writer.flyableStr(flyable, name, id)
				+ " registered to weather tower.";
		Writer.writer.addLine(str);
		if (Writer.writer.logs)
			System.out.println(str);
	}
	
	public static void unregisterTowerMsg(String flyable, String name, long id)
	{
		String	str;
		
		str = Writer.flyableStr(flyable, name, id) + " landing.";
		Writer.writer.addLine(str);
		if (Writer.writer.logs)
			System.out.println(str);
		str = "Tower says : " + Writer.flyableStr(flyable, name, id)
				+ " unregistered from weather tower.";
		Writer.writer.addLine(str);
		if (Writer.writer.logs)
			System.out.println(str);
	}
	
	public static void weatherChangeMsg(String weather, String flyable, String name, long id)
	{
		String	str;

		str = Writer.flyableStr(flyable, name, id) + ": "
				+ Writer.getWeatherStr(flyable, weather);
		Writer.writer.addLine(str);
		if (Writer.writer.logs)
			System.out.println(str);
	}
}
