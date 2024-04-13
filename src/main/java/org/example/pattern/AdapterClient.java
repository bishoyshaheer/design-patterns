package org.example.pattern;

class WeatherService {
    String getWeather(String commaSeparated) {
        if ("1.002,.982".equals(commaSeparated)) {
            return "temperature: 5º C!";
        } else {
            return "temperature: 10º C!";
        }
    }
}

class Location {
    String lat, lon;

    public Location(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }
}

class WeatherServiceAdapter extends WeatherService {
    private WeatherService weatherService;

    public WeatherServiceAdapter(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    String getWeather(Location location) {
        validate(location);
        return weatherService.getWeather(location.lat + "," + location.lon);
    }

    private void validate(Location location) {
        if (location.lat == null || location.lon == null) {
            throw new IllegalArgumentException("Location must not be null");
        }
    }
}

public class AdapterClient {
    public static void main(String[] args) {
        Location location = new Location("1.002", ".982");
        WeatherServiceAdapter adapter = new WeatherServiceAdapter(new WeatherService());
        System.out.println(adapter.getWeather(location));
    }
}
