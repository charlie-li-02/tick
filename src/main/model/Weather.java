package model;

import network.ReadWebPageWeather;

import java.io.IOException;
import java.text.DecimalFormat;

public class Weather implements AbstractSubject {

    private WeatherPrinter weatherPrinter;
    private String description;
    private Double temperature;
    private Double maxTemp;
    private Double minTemp;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private ReadWebPageWeather readWebPageWeather;

    public Weather() {

    }

    public void getWeather() throws IOException {
        readWebPageWeather = new ReadWebPageWeather();
        readWebPageWeather.readWeather();
        description = readWebPageWeather.parseWeather();
        temperature = readWebPageWeather.parseTemperature();
        maxTemp = readWebPageWeather.parseMaxTemperature();
        minTemp = readWebPageWeather.parseMinTemperature();
    }

    @Override
    public void addObservers(WeatherPrinter weatherPrinter) {
        this.weatherPrinter = weatherPrinter;
    }

    @Override
    public void notifyObservers() {
        weatherPrinter.update(description, df.format(temperature), df.format(maxTemp), df.format(minTemp));
    }

    public String getDescription() {
        return description;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public WeatherPrinter getObservers() {
        return weatherPrinter;
    }
}
