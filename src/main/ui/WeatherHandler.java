package ui;

import model.Weather;
import model.WeatherPrinter;
import java.io.IOException;

public class WeatherHandler {

    private Window window;
    private Weather weather;
    private WeatherPrinter weatherPrinter;

    public WeatherHandler(Window window) {
        this.window = window;
        weather = new Weather();
        weatherPrinter = new WeatherPrinter();
        weather.addObservers(weatherPrinter);
    }

    public void displayWeather() throws IOException {
        weather.getWeather();
        weather.notifyObservers();
        window.getWeatherLabel().setText(weatherPrinter.printWeather());
        window.getTemperatureLabel().setText(weatherPrinter.printTemperature());
    }
}
