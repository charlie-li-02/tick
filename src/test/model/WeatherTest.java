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
        assertEquals(0, weather.getSize());

        WeatherPrinter weatherPrinter = new WeatherPrinter();

        weather.addObservers(weatherPrinter);
        assertEquals(1, weather.getSize());

        weather.addObservers(weatherPrinter);
        assertEquals(2, weather.getSize());
    }

    @Test
    void testNotifyObservers() {

        //TESTING WITH THE ACTUAL API
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


        //TESTING MANUALLY FOR CODE COVERAGE
        weather.setDescription("Sunny");
        weather.setTemperature(10.0);
        weather.setMaxTemp(12.0);
        weather.setMinTemp(1.0);

        weather.notifyObservers();
        assertEquals(weather.getDescription(), weatherPrinter.getWeatherDescription());
        assertEquals(df.format(weather.getTemperature()), weatherPrinter.getTemperature());
        assertEquals(df.format(weather.getMaxTemp()), weatherPrinter.getMaxTemp());
        assertEquals(df.format(weather.getMinTemp()), weatherPrinter.getMinTemp());

    }

    @Test
    void testForSetterAndGetters() {
        //TESTING SETTING THE FIELDS MANUALLY FOR CODE COVERAGE
        weather.setDescription("Sunny");
        weather.setTemperature(10.0);
        weather.setMaxTemp(12.0);
        weather.setMinTemp(1.0);

        assertEquals("Sunny", weather.getDescription());
        assertEquals(10.0, weather.getTemperature());
        assertEquals(12.0, weather.getMaxTemp());
        assertEquals(1.0, weather.getMinTemp());
    }

}
