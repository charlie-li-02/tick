package model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject {

    protected List<AbstractObserver> observers = new ArrayList<>();

    public AbstractSubject() {}

    public void addObservers(WeatherPrinter weatherPrinter) {
        observers.add(weatherPrinter);
    }

    public void notifyObservers() {
        for (AbstractObserver observer: observers) {
            observer.update("", "", "", "");
        }
    }

    public int getSize() {
        int size = 0;
        for (AbstractObserver observer: observers) {
            size++;
        }
        return size;
    }
}
