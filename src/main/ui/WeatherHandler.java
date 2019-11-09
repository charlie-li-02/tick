package ui;

import model.Weather;
import model.WeatherPrinter;
import java.io.IOException;

public class WeatherHandler {

    private Weather weather;
    private WeatherPrinter weatherPrinter;

    public WeatherHandler() {
        weather = new Weather();
        weatherPrinter = new WeatherPrinter();
        weather.addObservers(weatherPrinter);
    }

    public void displayWeather() throws IOException {
        weather.getWeather();
        weather.notifyObservers();
        System.out.println(weatherPrinter.print());
    }
}
