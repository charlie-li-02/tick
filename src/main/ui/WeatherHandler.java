package ui;

import model.Weather;
import model.WeatherPrinter;
import java.io.IOException;

public class WeatherHandler {

    public WeatherHandler() {}

    public void displayWeather() throws IOException {
        Weather weather = new Weather();
        weather.getWeather();
        WeatherPrinter weatherPrinter = new WeatherPrinter();
        weather.addObservers(weatherPrinter);
        weather.notifyObservers();
        System.out.println(weatherPrinter.print());
    }
}
