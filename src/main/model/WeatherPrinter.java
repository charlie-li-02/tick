package model;

public class WeatherPrinter implements AbstractObserver {

    private String weatherDescription;
    private String temperature;
    private String maxTemp;
    private String minTemp;


    public WeatherPrinter() {}


    @Override
    public void update(String weatherDescription, String temperature, String maxTemp, String minTemp) {
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public String printWeather() {
        return "Today's weather in Vancouver: " + weatherDescription;
    }

    public String printTemperature() {
        return  "The temperature is: " + temperature + " °C"
                + ", high of: " + maxTemp + " °C"
                + " and low of: " + minTemp + " °C";
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
