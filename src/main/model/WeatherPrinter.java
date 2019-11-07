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

    public String print() {
        return "Today's weather in Vancouver: " + weatherDescription + "\n"
                + "The temperature today is: " + temperature + " °C" + "\n"
                + "With a high of: " + maxTemp + " °C" + "\n"
                + "And a low of: " + minTemp + " °C" + "\n";
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
