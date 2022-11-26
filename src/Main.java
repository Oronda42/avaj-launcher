package src;

import java.util.ArrayList;

import src.parsing.Config;
import src.parsing.FileParser;
import src.tower.WeatherTower;

public class Main {
	public static void main(String[] argv) {

        if(argv.length != 1) {
            System.out.println("Please provide a simulation file");
            return;
        }
       
		FileParser fileParser = new FileParser();
		Config config =  fileParser.Parse(argv[0]);
        if (config == null) {
            Logger.getInstance().close();
            return;
        }

		ArrayList<Flyable> flyables = new ArrayList<Flyable>();
		AircraftFactory aircraftFactory = new AircraftFactory();
		
		for (Config.Data d : config.data) {

			Flyable f =  aircraftFactory.newAircraft(d.type , d.name, d.longitude,d.latitude,d.height);
			flyables.add(f);
		}

		WeatherTower weatherTower = new WeatherTower();

		for (Flyable f : flyables)
		{
			f.registerTower(weatherTower);
		}

		while(config.weatherChangeCount > 0)
		{
            weatherTower.changeWeather();
            config.weatherChangeCount--;
		}

        Logger.getInstance().close();
	}
}