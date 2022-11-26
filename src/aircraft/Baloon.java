package src.aircraft;

import src.Coordinates;
import src.Flyable;
import src.Logger;
import src.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
    

	public Baloon(String name, Coordinates coordinates){
		
		super(name, coordinates);
	}
	public void updateConditions() {
        
        String weather = weatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
                Logger.getInstance().log("Baloon#" + this.name + "(" + this.id + "): " + "Let's enjoy the good weather and take some pics.");
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
                Logger.getInstance().log("Baloon#" + this.name + "(" + this.id + "): " + "Damn you rain! You messed up my baloon.");
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
                Logger.getInstance().log("Baloon#" + this.name + "(" + this.id + "): " + "I can't see anything!");
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
                Logger.getInstance().log("Baloon#" + this.name + "(" + this.id + "): " + "It's snowing. We're gonna crash.");
                break;
        }
        if (this.coordinates.getHeight() <= 0) {
            Logger.getInstance().log("Baloon#" + this.name + "(" + this.id + "): " + "landing.");
            this.weatherTower.unregister(this);
            Logger.getInstance().log("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
        else if (this.coordinates.getHeight() > 100) {
            this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), 100);
        }
	}

	public void registerTower(WeatherTower weatherTower) {
		
		this.weatherTower = weatherTower;
	
		this.weatherTower.register(this);
	}
}

 
