package Simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Flyables.AircraftFactory;
import Flyables.Flyable;
import Weather.Simulation;


/*
 * Class to parse the input file. Keep it in track with a BufferdReader, and the current line.
 */

public class Parser {
	private BufferedReader	input;
	private	int				lineNb;
	
	
	/*
	 * Constructor
	 */
	
	Parser(File file) throws FileNotFoundException
	{
		this.input = new BufferedReader(new FileReader(file));
		this.lineNb = 0;
	} 
	
	
	/*
	 * Read the first line (should be a positive integer) to get the number of simulations
	 */
	
	int getSimulationsNb() throws Exception
	{
		int		simulationsNb;
		String	str;
		
		str = this.input.readLine();
		this.lineNb += 1;
		if ((simulationsNb = Integer.parseInt(str)) < 1)
		{
			throw new Exception(MyErrors.error("SIMNB"));
		}
		return simulationsNb;
	}
	
	
	/*
	 * Read a line on which an aircraft's information is written, a return it as a Flyable
	 */
	
	Flyable readFlyable(AircraftFactory aircraftFactory) throws Exception
	{
		String		line;
		String[]	splitLine;
		Flyable		flyable;

		splitLine = new String[0];
		flyable = null;
		while (splitLine.length == 0)
		{
			this.lineNb += 1;
			if ((line = this.input.readLine()) == null)
				return null;
			splitLine = line.split(" ");
			if (splitLine.length == 0)
				continue ;
			if (splitLine.length != 5)
				throw new Exception(MyErrors.error("FINFO", this.lineNb));
			if (!Simulation.airCrafts.contains(splitLine[0]))
				throw new Exception(MyErrors.error("FLIST", this.lineNb));
			try {
				flyable = aircraftFactory.newAircraft(splitLine[0], splitLine[1],
						Integer.parseUnsignedInt(splitLine[2]),
						Integer.parseUnsignedInt(splitLine[3]),
						Integer.parseUnsignedInt(splitLine[4]));
			} catch (Exception e) {
				throw new Exception(MyErrors.error("COO", this.lineNb));
			}
		}
		return flyable;
	}
}
