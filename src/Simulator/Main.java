package Simulator;

import java.io.File;

import Flyables.Flyable;
import Weather.Simulation;

public class Main {
	public static void main(String[] argv)
	{
		Parser			parser;
		Writer			writer;
		Flyable			flyable;
		Simulation		simulation;
		
		MyErrors.initialize();
		try {
			if (argv.length != 1)
				throw new Exception(MyErrors.error("ARG"));
			parser = new Parser(new File(argv[0]));
			simulation = new Simulation(parser.getSimulationsNb());
			while ((flyable = parser.readFlyable(simulation.getFactory())) != null)
			{
				simulation.addFlyable(flyable);
			}
			writer = Writer.getWriter();
			simulation.start();
			writer.closeFile();
		} catch (Exception exception) {
			System.err.println("Error : " + exception.getMessage());
			return ;
		}
	}
}
