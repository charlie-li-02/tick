package model;

import exceptions.DeletingNoneExistentItem;
import exceptions.MarkingNoneExistentItem;
import exceptions.TooManyItemsUndoneException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ListOfItems implements Save, Load {

    public ArrayList<Item> listOfItems;
    public int maxUndone = 10;

    public ListOfItems() {
        this.listOfItems = new ArrayList<>();
    }

    //REQUIRES: nothing
    //MODIFIES: Item
    //EFFECTS: creates a new Item
    public abstract Item itemMaker(String title, String attribute);

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds the Item onto the list of items
    public void addItem(Item item) {
        listOfItems.add(item);
    }

    public void addNewItem(Item item) throws TooManyItemsUndoneException {
        int undoneItems = 0;
        for (Item i: listOfItems) {
            if (!i.getIsDone()) {
                undoneItems++;
            }
        }
        if (undoneItems >= maxUndone) {
            throw new TooManyItemsUndoneException();
        } else {
            listOfItems.add(item);
        }
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the ith Item
    public Item get(int i) {
        return listOfItems.get(i);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the size of the array
    public int getSize() {
        return listOfItems.size();
    }

    //REQUIRES: at least one item in the array
    //MODIFIES: this
    //EFFECTS: removes the ith Item
    public Item remove(int i) throws DeletingNoneExistentItem {
        if (i + 1 > listOfItems.size()) {
            throw new DeletingNoneExistentItem();
        } else {
            return listOfItems.remove(i);
        }
    }

    //REQUIRES: at least one item in the array
    //MODIFIES: this
    //EFFECTS: changes the status ith Item
    public void changeStatus(int i) throws MarkingNoneExistentItem {
        if (i + 1 > listOfItems.size()) {
            throw new MarkingNoneExistentItem();
        } else {
            listOfItems.get(i).flipStatus();
        }
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

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptTitle
    public abstract String getPromptTitle();

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptAnother
    public abstract String getPromptAnother();

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the SavePath
    public abstract String getSavePath();

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: loads the items in the save file into the list
    public abstract void load() throws IOException;

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS saves the items in the list to the save file
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter fileClearer = new PrintWriter(getSavePath(), "UTF-8");
        fileClearer.close();
        PrintWriter writer = new PrintWriter(getSavePath(), "UTF-8");
        for (Item item: listOfItems) {
            String line = merge(item);
            writer.println(line);
        }
        writer.close();
    }

}
