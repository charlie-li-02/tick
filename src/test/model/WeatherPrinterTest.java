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

        weatherPrinter.update("Sunny", "10", "14", "5");

        assertEquals("Sunny", weatherPrinter.getWeatherDescription());
        assertEquals("10", weatherPrinter.getTemperature());
        assertEquals("14", weatherPrinter.getMaxTemp());
        assertEquals("5", weatherPrinter.getMinTemp());

        weatherPrinter.update("Rainy", "3", "11", "2");

        assertEquals("Rainy", weatherPrinter.getWeatherDescription());
        assertEquals("3", weatherPrinter.getTemperature());
        assertEquals("11", weatherPrinter.getMaxTemp());
        assertEquals("2", weatherPrinter.getMinTemp());
    }

    @Test
    void testPrint() {
        weatherPrinter.update("Sunny", "10", "14", "5");

        String sunnyExpected = "Today's weather in Vancouver: Sunny\n"
                            + "The temperature today is: 10 °C\n"
                            + "With a high of: 14 °C\n"
                            + "And a low of: 5 °C\n";

        assertEquals(sunnyExpected, weatherPrinter.print());


        weatherPrinter.update("Rainy", "3", "11", "2");

        String rainyExpected = "Today's weather in Vancouver: Rainy\n"
                            + "The temperature today is: 3 °C\n"
                            + "With a high of: 11 °C\n"
                            + "And a low of: 2 °C\n";

        assertEquals(rainyExpected, weatherPrinter.print());
    }


}
