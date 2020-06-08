package Simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import Weather.Simulation;


/*
 * Class to write the output file describing what happened during the simulation.
 * Logs can be set to true to print messages on the shell while writing the file.
 * Contains the name of the file and the output sentences of each aircraft depending
 * on the weather.
 */

public class Writer {
	private final boolean										logs = false;
	private final String										fileName = "simulation.txt";
	private static Hashtable<String, Hashtable<String, String>>	aircraftStr;
	private static Writer										writer = new Writer();
	private FileWriter											myWriter;
	
	
	/*
	 * Constructor
	 */
	
	private Writer()
	{
		try {
			this.myWriter = new FileWriter(this.fileName);
		} catch (IOException e) {
			MyErrors.error("WRITE");
		}
		Writer.initializer();
	}
	
	
	/*
	 * Initializes the HashTable containing strings for messages of aircrafts depending
	 * on the current weather.
	 */
	
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
				helicopterStr.put(weather, "My helix protects me from this rain.");
				break;
			case "FOG":
				baloonStr.put(weather, "Am i a cloud ? I can't see a meter away !");
				jetPlaneStr.put(weather, "Time to trust my softwares, i can't see anything in the fog.");
				helicopterStr.put(weather, "We need to advance slowly and pay attention with this low visibility.");
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
	
	
	/*
	 * Close the file when writing is done
	 */
	
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
	
	
	/*
	 * Add a new line in the file
	 */
	
	private void addLine(String line)
	{
		try {
			this.myWriter.write(line + "\n");
		} catch (IOException e) {
			System.err.println("Error : " + MyErrors.error("WRITE"));
			Writer.writer.closeFile();
		}
	}
	
	
	/*
	 * Get the message of an aircraft depending on the current weather.
	 */
	
	private static String getWeatherStr(String flyableKey, String weather)
	{
		return Writer.aircraftStr.get(flyableKey).get(weather);
	}
	
	
	/*
	 * Get the string of a flyable.
	 */
	
	private static String getFlyableStr(String flyable, String name, long id)
	{
		return flyable + "#" + name + "(" + String.valueOf(id) + ")";
	}
	
	
	/*
	 * Message written when an aircraft register to a weather tower.
	 */
	
	public static void registerTowerMsg(String flyable, String name, long id)
	{
		String	str;
		
		str = "Tower says: " + Writer.getFlyableStr(flyable, name, id)
				+ " registered to weather tower.";
		Writer.writer.addLine(str);
		if (Writer.writer.logs)
			System.out.println(str);
	}
	
	
	/*
	 * Message written when an aircraft unregister from a weather tower.
	 */
	
	public static void unregisterTowerMsg(String flyable, String name, long id)
	{
		String	str;
		
		str = Writer.getFlyableStr(flyable, name, id) + " landing.";
		Writer.writer.addLine(str);
		if (Writer.writer.logs)
			System.out.println(str);
		str = "Tower says : " + Writer.getFlyableStr(flyable, name, id)
				+ " unregistered from weather tower.";
		Writer.writer.addLine(str);
		if (Writer.writer.logs)
			System.out.println(str);
	}
	
	
	/*
	 * Message written by an aircraft under a certain weather
	 */
	
	public static void weatherChangeMsg(String weather, String flyable, String name, long id)
	{
		String	str;

		str = Writer.getFlyableStr(flyable, name, id) + ": "
				+ Writer.getWeatherStr(flyable, weather);
		Writer.writer.addLine(str);
		if (Writer.writer.logs)
			System.out.println(str);
	}
	
	
	/*
	 * Getter
	 */
	
	public static Writer getWriter()
	{
		return Writer.writer;
	}
}
