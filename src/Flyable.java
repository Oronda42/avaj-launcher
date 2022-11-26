package src;

import src.tower.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
