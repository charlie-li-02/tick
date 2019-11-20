package model;

import java.awt.image.BufferedImage;

public class WeatherPrinter implements AbstractObserver {

    private String weatherDescription;
    private String temperature;
    private String maxTemp;
    private String minTemp;
    private BufferedImage icon;


    public WeatherPrinter() {}


    @Override
    public void update(String weatherDescription, String temperature, String maxTemp, String minTemp,
                       BufferedImage icon) {
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.icon = icon;
    }

    //REQUIRES: weatherDescription not null
    //MODIFIES: nothing
    //EFFECTS: formats the weather message
    public String printWeather() {
        return weatherDescription;
    }

    //REQUIRES: temperature not null
    //MODIFIES: nothing
    //EFFECTS: formats the temperature message
    public String printTemperature() {
        return  temperature + "°C";
    }

    //REQUIRES: maxTemp not null
    //MODIFIES: nothing
    //EFFECTS: formats the max temperature message
    public String printMaxTemp() {
        return "high: " + maxTemp + "°C";
    }

    //REQUIRES: minTemp not null
    //MODIFIES: nothing
    //EFFECTS: formats the min temperature message
    public String printMinTemp() {
        return  "low:  " + minTemp + "°C";
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }
}
