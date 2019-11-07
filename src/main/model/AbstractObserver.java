package model;

public interface AbstractObserver {

    void update(String weatherDescription, String temperature, String maxTemp, String minTemp);
}
