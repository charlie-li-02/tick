package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Save {

    //REQUIRES: the save file is in the right path
    //MODIFIES: the save file
    //EFFECTS: adds the items into the save file
    void save() throws FileNotFoundException, UnsupportedEncodingException;
}
