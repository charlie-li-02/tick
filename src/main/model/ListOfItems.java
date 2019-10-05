package model;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ListOfItems {
    public ArrayList<Item> listOfItem;

    public ListOfItems(ArrayList<Item> li) {
        this.listOfItem = li;
    }

    //REQUIRES: nothing
    //MODIFIES: Item
    //EFFECTS: creates a new Item
    public abstract Item itemMaker(String title, String attribute);

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds the Item onto the list of items
    public void addItem(Item item) {
        this.listOfItem.add(item);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the ith Item
    public Item get(int i) {
        return this.listOfItem.get(i);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the size of the array
    public int getSize() {
        return this.listOfItem.size();
    }

    //REQUIRES: at least one item in the array
    //MODIFIES: this
    //EFFECTS: removes the ith Item
    public Item remove(int i) {
        return this.listOfItem.remove(i);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: splits a single line of the save file into pieces at where there are ";"
    public static ArrayList<String> split(String line) {
        String[] splits = line.split(";");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: converts an item into an entry for the save file
    public static String merge(Item item) {
        String entry = item.getTitle() + ";" + item.getAttribute() + ";" + booleanToString(item.getIsDone());
        return entry;
    }

    //REQUIRES: s to be either "true" or "false"
    //MODIFIES: nothing
    //EFFECTS: converts a string representation of a boolean value into a boolean
    public static Boolean stringToBoolean(String s) {
        if (s.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: converts a boolean into a string representation of that boolean
    public static String booleanToString(Boolean b) {
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: formats the list of items into a list of string
    public abstract ArrayList<String> print();
}
