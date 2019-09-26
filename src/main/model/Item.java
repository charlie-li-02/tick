package model;

public interface Item {

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the title of the to do
    public String getTitle();

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the description of the to do
    public String getAttribute();

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns whether an item is done or not
    Boolean getIsDone();

    //REQUIRES: nothing
    //MODIFIES: noting
    //EFFECTS: formats the item it was called on and coverts it to a string
    String toString();

    //REQUIRES: nothing
    //MODIFIES: item
    //EFFECTS: sets the item as done represented by true
    void markDone();

    //REQUIRES: nothing
    //MODIFIES: item
    //EFFECTS: sets the item as undone represented by false
    void markUndone();

    //REQUIRES: nothing
    //MODIFIES: item
    //EFFECTS:  flips the status of the item
    void flipStatus();
}
