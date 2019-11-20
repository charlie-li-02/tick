package model;

import java.awt.image.BufferedImage;

public interface AbstractObserver {

    void update(String weatherDescription, String temperature, String maxTemp, String minTemp, BufferedImage icon);
}
