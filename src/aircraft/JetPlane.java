package src.aircraft;

import src.Coordinates;
import src.Flyable;
import src.Logger;
import src.tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
                Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "): " + "This is so f***** hot.");
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
                Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "): " + "It's raining. Better watch out for lightings");
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude() , coordinates.getLatitude() + 1, coordinates.getHeight());
                Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "): " + "I can't see anything!");
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
                Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "): " + "OMG! Winter is coming!");
                break;
        }
        if (this.coordinates.getHeight() <= 0) {
            Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "): " + "landing.");
            this.weatherTower.unregister(this);
            Logger.getInstance().log("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
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




