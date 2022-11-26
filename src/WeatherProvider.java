package src;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int weatherIndex = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
        return weather[weatherIndex % 4];
    }
}
