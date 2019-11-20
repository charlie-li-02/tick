package ui;

import model.Weather;
import model.WeatherPrinter;

import javax.swing.*;
import java.io.IOException;

public class WeatherHandler {

    private Window window;
    private Weather weather;
    private WeatherPrinter weatherPrinter;

    //REQUIRES: nothing
    //MODIFIES: this, weather
    //EFFECTS: constructor for WeatherHandler
    public WeatherHandler(Window window) {
        this.window = window;
        weather = new Weather();
        weatherPrinter = new WeatherPrinter();
        weather.addObservers(weatherPrinter);
    }

    //REQUIRES: nothing
    //MODIFIES: Window
    //EFFECTS: sets the text fields of 2 labels after getting the data from weather
    public void displayWeather() throws IOException {
        weather.getWeather();
        weather.notifyObservers();
        window.getWeatherLabel().setText(weatherPrinter.printWeather());
        window.getTemperatureLabel().setText(weatherPrinter.printTemperature());
        window.getMaxTempLabel().setText(weatherPrinter.printMaxTemp());
        window.getMinTempLabel().setText(weatherPrinter.printMinTemp());
        window.getWeatherIcon().setIcon(new ImageIcon(weatherPrinter.getIcon()));
    }
}
