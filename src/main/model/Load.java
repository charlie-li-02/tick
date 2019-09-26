package model;

import java.io.IOException;

public interface Load {

    //REQUIRES: the save is in the right path
    //MODIFIES: List, Item
    //EFFECTS: reads the save file, create new items and add them into the list
    void load(String path) throws IOException;
}
