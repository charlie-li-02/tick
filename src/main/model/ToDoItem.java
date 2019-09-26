package model;

public class ToDoItem implements Item {
    private String title;
    private String description;
    private Boolean isDone;

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: constructor for ToDoItem
    public ToDoItem(String title, String description, Boolean isDone) {
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the title of the to do
    public String getTitle() {
        return this.title;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns the description of the to do
    public String getDescription() {
        return this.description;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: returns whether a to do is done or not
    public Boolean getIsDone() {
        return this.isDone;
    }

    //REQUIRES: nothing
    //MODIFIES: noting
    //EFFECTS: formats the to do it was called on and coverts it to a string
    public String toString() {
        return "Title: " + this.getTitle() + ", Description: " + this.getDescription() + ", Done?: " + this.getIsDone();
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
        if (this.getIsDone()) {
            this.markUndone();
        } else {
            this.markDone();
        }
    }

}
