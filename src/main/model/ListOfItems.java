package model;

import exceptions.DeletingNoneExistentItem;
import exceptions.MarkingNoneExistentItem;
import exceptions.TooManyItemsUndoneException;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ListOfItems {
    public ArrayList<Item> listOfItem;
    public int maxUndone = 10;

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

    public void addNewItem(Item item) throws TooManyItemsUndoneException {
        int undoneItems = 0;
        for (Item i: this.listOfItem) {
            if (!i.getIsDone()) {
                undoneItems++;
            }
        }
        if (undoneItems >= maxUndone) {
            throw new TooManyItemsUndoneException();
        } else {
            this.listOfItem.add(item);
        }
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
    public Item remove(int i) throws DeletingNoneExistentItem {
        if (i + 1 > this.listOfItem.size()) {
            throw new DeletingNoneExistentItem();
        } else {
            return this.listOfItem.remove(i);
        }
    }

    //REQUIRES: at least one item in the array
    //MODIFIES: this
    //EFFECTS: changes the status ith Item
    public void changeStatus(int i) throws MarkingNoneExistentItem {
        if (i + 1 > this.listOfItem.size()) {
            throw new MarkingNoneExistentItem();
        } else {
            listOfItem.get(i).flipStatus();
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
    //EFFECTS: returns the PromptAttribute
    public abstract String getPromptAttribute();

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the PromptAnother
    public abstract String getPromptAnother();
}
