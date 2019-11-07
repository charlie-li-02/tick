package model;

import network.ReadWebPageWeather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherTest {

    private static DecimalFormat df = new DecimalFormat("0.00");
    private Weather weather;
    private ReadWebPageWeather readWebPageWeather;

    @BeforeEach
    void setup() {
        weather = new Weather();
        readWebPageWeather = new ReadWebPageWeather();

    }


    @Test
    void testGetWeather() {
        try {
            weather.getWeather();
            readWebPageWeather.readWeather();
            assertEquals(readWebPageWeather.parseWeather(), weather.getDescription());
            assertEquals(readWebPageWeather.parseTemperature(), weather.getTemperature());
            assertEquals(readWebPageWeather.parseMaxTemperature(), weather.getMaxTemp());
            assertEquals(readWebPageWeather.parseMinTemperature(), weather.getMinTemp());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testAddObservers() {
        assertEquals(null, weather.getObservers());
        WeatherPrinter weatherPrinter = new WeatherPrinter();
        weather.addObservers(weatherPrinter);
        assertEquals(weatherPrinter, weather.getObservers());

        WeatherPrinter newWeatherPrinter = new WeatherPrinter();
        weather.addObservers(newWeatherPrinter);
        assertEquals(newWeatherPrinter, weather.getObservers());
    }

    @Test
    void testNotifyObservers() {
        WeatherPrinter weatherPrinter = new WeatherPrinter();
        weather.addObservers(weatherPrinter);

        try {
            weather.getWeather();
            weather.notifyObservers();
            assertEquals(weather.getDescription(), weatherPrinter.getWeatherDescription());
            assertEquals(df.format(weather.getTemperature()), weatherPrinter.getTemperature());
            assertEquals(df.format(weather.getMaxTemp()), weatherPrinter.getMaxTemp());
            assertEquals(df.format(weather.getMinTemp()), weatherPrinter.getMinTemp());
        } catch (IOException e) {
            fail();
        }
    }

}
