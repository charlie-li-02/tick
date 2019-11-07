package model;

public interface AbstractSubject {

    void addObservers(WeatherPrinter weatherPrinter);

    void notifyObservers();
}
