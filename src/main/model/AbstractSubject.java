package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject {

    private List<AbstractObserver> observers = new ArrayList<>();
    private String description;
    private Double temperature;
    private Double maxTemp;
    private Double minTemp;
    private static DecimalFormat df = new DecimalFormat("0.00");

    public AbstractSubject() {
        description = null;
        temperature = null;
        maxTemp = null;
        minTemp = null;
    }

    public void addObservers(WeatherPrinter weatherPrinter) {
        observers.add(weatherPrinter);
    }

    public void notifyObservers() {
        for (AbstractObserver observer: observers) {
            observer.update(description, df.format(temperature), df.format(maxTemp), df.format(minTemp));
        }
    }
}
