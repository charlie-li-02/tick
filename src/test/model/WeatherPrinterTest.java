package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherPrinterTest {

    private WeatherPrinter weatherPrinter;

    @BeforeEach
    void setup() {
        weatherPrinter = new WeatherPrinter();
    }

    @Test
    void testUpdate() {
        assertEquals(null, weatherPrinter.getWeatherDescription());
        assertEquals(null, weatherPrinter.getTemperature());
        assertEquals(null, weatherPrinter.getMaxTemp());
        assertEquals(null, weatherPrinter.getMinTemp());

        weatherPrinter.update("Sunny", "10", "14", "5", null);

        assertEquals("Sunny", weatherPrinter.getWeatherDescription());
        assertEquals("10", weatherPrinter.getTemperature());
        assertEquals("14", weatherPrinter.getMaxTemp());
        assertEquals("5", weatherPrinter.getMinTemp());

        weatherPrinter.update("Rainy", "3", "11", "2", null);

        assertEquals("Rainy", weatherPrinter.getWeatherDescription());
        assertEquals("3", weatherPrinter.getTemperature());
        assertEquals("11", weatherPrinter.getMaxTemp());
        assertEquals("2", weatherPrinter.getMinTemp());
    }

    @Test
    void testPrint() {
        weatherPrinter.update("Sunny", "10", "14", "5", null);

        String sunnyExpected = "Today's weather in Vancouver is: Sunny";

        assertEquals(sunnyExpected, weatherPrinter.printWeather());


        weatherPrinter.update("Rainy", "3", "11", "2", null);

        String rainyExpected = "Today's weather in Vancouver is: Rainy";

        assertEquals(rainyExpected, weatherPrinter.printWeather());
    }


}
