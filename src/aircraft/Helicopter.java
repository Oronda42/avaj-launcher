package src.aircraft;

import src.Coordinates;
import src.Flyable;
import src.Logger;
import src.tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "): " + "I am roasting.");
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
                Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "): " + "Damn you rain! Slow down my rotor.");
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
                Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "): " + "I can't see anything!");
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
                Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "): " + "My rotor is going to freeze!.");
                break;
        }
        if (this.coordinates.getHeight() <= 0) {
            Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "): " + "landing.");
            this.weatherTower.unregister(this);
            Logger.getInstance().log("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
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


