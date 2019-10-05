package model;

public class Item {
    private String title;
    private String attribute;
    private Boolean isDone;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for Item
    public Item(String title, String attribute, Boolean isDone) {
        this.title = title;
        this.attribute = attribute;
        this.isDone = isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the title of the item
    public String getTitle() {
        return title;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the attribute of the item
    public String getAttribute() {
        return attribute;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns whether an item is done or not
    public Boolean getIsDone() {
        return isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: noting
    //EFFECTS: formats the item it was called on and coverts it to a string
    public String toString() {
        return "Title: " + title + ", Description: " + attribute + ", Done?: " + isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: sets the this as done represented by true
    public void markDone() {
        this.isDone = true;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: sets the this as undone represented by false
    public void markUndone() {
        this.isDone = false;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS:  flips the status of this
    public void flipStatus() {
        if (this.isDone) {
            markUndone();
        } else {
            markDone();
        }
    }

}
