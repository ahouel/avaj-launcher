package Simulator;

import java.util.Hashtable;


/*
 * Custom errors messages based on keys, should be initialized before use.
 */

public abstract class MyErrors {
	private static Hashtable<String, String> errorStr = new Hashtable<String, String>();

	
	/*
	 * Initialize the error Hashtable
	 */
	
	public static void initialize()
	{
		MyErrors.errorStr.put("SIMNB", "First line of the scenario must be a positive integer.");
		MyErrors.errorStr.put("ARG", "Simulation should take one scenario file as argument.");
		MyErrors.errorStr.put("FINFO", "A line describing an aircraft should have 5 arguments.");
		MyErrors.errorStr.put("FLIST", "This aircraft's type is not valable.");
		MyErrors.errorStr.put("COO", "Coordinates must be positive integers.");
		MyErrors.errorStr.put("WRITE", "An error occured while writting the output file.");
		MyErrors.errorStr.put("CLOSE", "An error occured while closing the output file.");
	}
	
	
	/*
	 * Get the error message depending on the key, adding the line where it occurred if provided.
	 */
	
	public static String error(String key)
	{
		return MyErrors.errorStr.get(key);
	}
	
	public static String error(String key, int line)
	{
		return "Line : " + String.valueOf(line) + " " + MyErrors.errorStr.get(key);
	}
}
